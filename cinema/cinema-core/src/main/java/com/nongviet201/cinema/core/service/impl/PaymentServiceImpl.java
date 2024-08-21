package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.entity.bill.Reservation;
import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.enums.bill.BillStatus;
import com.nongviet201.cinema.core.model.enums.bill.PaymentMethod;
import com.nongviet201.cinema.core.model.enums.showtime.ReservationType;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;
import com.nongviet201.cinema.core.payment.vnpay.service.VnPayService;
import com.nongviet201.cinema.core.repository.BillRepository;
import com.nongviet201.cinema.core.repository.ReservationRepository;
import com.nongviet201.cinema.core.repository.TranslationRepository;
import com.nongviet201.cinema.core.request.ToPaymentRequest;
import com.nongviet201.cinema.core.request.VnPayReturnRequest;

import com.nongviet201.cinema.core.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final VnPayService vnPayService;
    private final BillRepository billRepository;
    private final TranslationRepository translationRepository;
    private final UserService userService;
    private final ReservationService reservationService;
    private final BillSeatService billSeatService;
    private final ReservationRepository reservationRepository;
    private final UserStatisticService userStatisticService;


    @Override
    public String transitionToPaymentMethod(
        ToPaymentRequest request
    ) {
        if (request.getPaymentMethod() == PaymentMethod.VNPAY) {
            return vnPayService.createPayment(
                request.getTranslationId(),
                request.getAmount(),
                request.getTimeRemain()
            );
        }
        throw new BadRequestException("Người dùng chưa chọn phuơng thức thanh toán");
    }

    @Override
    public Integer PaymentVnPayReturnCheck(
        VnPayReturnRequest request
    ) {
        Translation translation = translationRepository.findById(request.getTransitionId())
            .orElseThrow(() -> new BadRequestException("Không tìm thấy thông tin giao dich"));

        Bill bill = translation.getBill();

        checkBill(bill);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        bill.setPaymentAt(LocalDateTime.parse(request.getPayDate(), formatter));

        translation.setBankCode(request.getBankCode());
        translation.setTransactionNo(request.getTransactionNo());
        translation.setPayDate(LocalDateTime.parse(request.getPayDate(), formatter));

        checkVnPayResponseCode(
            bill,
            translation,
            request.getAmount(),
            ResponseCodeVNPAY.fromCode(request.getResponseCode())
        );

        return bill.getId();
    }

    private void checkBill(
        Bill bill
    ) {
        if (bill.getStatus() == BillStatus.PENDING_PROCESSING) {
            throw new BadRequestException("Đơn hàng đang trong quá trình xử lý");
        } else if (bill.getStatus() == BillStatus.CANCEL) {
            throw new BadRequestException("Đơn hàng đã bị hủy");
        } else if (bill.getStatus() == BillStatus.FAILED) {
            throw new BadRequestException("Đơn hàng này gặp phải tình trạng lỗi, vui lòng đặt lại đơn mới");
        }
    }

    private void updateBill(
        Bill bill,
        Translation translation,
        ResponseCodeVNPAY responseCode,
        boolean status,
        BillStatus billStatus
    ) {
        translation.setResponseCodeVNPAY(responseCode);
        translation.setStatus(status);
        translation.setUpdatedAt(now());
        translationRepository.save(translation);

        bill.setStatus(billStatus);
        bill.setUpdatedAt(now());
        bill.setBarcode("https://quickchart.io/barcode?type=code128&text=" + translation.getTransactionNo() + "&width=600&height=200");
        billRepository.save(bill);
    }

    private void billSuccess(
        Bill bill,
        int userId,
        int showtimeId
    ) {
        billSeatService.getBillSeatByBillId(bill.getId()).forEach(
            billSeat -> {
                updateReservation(
                    userId,
                    showtimeId,
                    billSeat.getSeat().getId()
                );
            });
        bill.setPoints(userStatisticService.UpdateUserStatistic(bill.getTotalPrice()));
        billRepository.save(bill);
    }

    private void updateReservation(
        Integer userId,
        Integer showtimeId,
        Integer seatId
    ){
        Reservation reservation = reservationService.getReservationByUserIdAndShowtimeIdAndSeatId(userId, showtimeId, seatId);
        reservation.setStatus(ReservationType.ORDERED);
        reservation.setUpdatedAt(now());
        reservationRepository.save(reservation);
    }

    private void checkVnPayResponseCode(
        Bill bill,
        Translation translation,
        long requestAmount,
        ResponseCodeVNPAY responseCode
    ) {
        if (responseCode == ResponseCodeVNPAY.PAYMENT_CANCEL) {
            updateBill(bill, translation, responseCode, false, BillStatus.CANCEL);
        }
        if (responseCode != ResponseCodeVNPAY.PAYMENT_SUCCESS) {
            updateBill(bill, translation, responseCode, false, BillStatus.FAILED);
        } else {
            // kiểm tra nếu thanh toán thành công nhưng số tiền không khớp nhau thì gán lỗi
            if ((requestAmount / 100) != bill.getTotalPrice()) {
                translation.setResponseCodeVNPAY(ResponseCodeVNPAY.PAYMENT_ERROR_AMOUNT);
                updateBill(bill, translation, responseCode, false, BillStatus.PENDING_PROCESSING);
            }
            // kiểm tra nếu thanh toán thành công nhưng user thanh toán không phải user hiện tại thì gán lỗi
            else if (!bill.getUser().getId().equals(userService.getCurrentUser().getId())) {
                translation.setResponseCodeVNPAY(ResponseCodeVNPAY.PAYMENT_ERROR_USER);
                updateBill(bill, translation, responseCode, false, BillStatus.PENDING_PROCESSING);
            }
            // nếu không có lỗi gì thì cho bill thành công
            else {
                updateBill(bill, translation, responseCode, true, BillStatus.PAID);
                billSuccess(bill, bill.getUser().getId(), bill.getShowtime().getId());
            }
        }
    }

}

