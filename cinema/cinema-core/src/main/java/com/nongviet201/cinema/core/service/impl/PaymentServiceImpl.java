package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.entity.bill.Reservation;
import com.nongviet201.cinema.core.entity.bill.TranslationPayment;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.enums.BillStatus;
import com.nongviet201.cinema.core.model.enums.PaymentMethod;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import com.nongviet201.cinema.core.repository.BillRepository;
import com.nongviet201.cinema.core.repository.ReservationRepository;
import com.nongviet201.cinema.core.repository.TranslationPaymentRepository;
import com.nongviet201.cinema.core.request.ToPaymentRequest;
import com.nongviet201.cinema.core.request.VnPayReturnRequest;
import com.nongviet201.cinema.core.service.*;
import com.nongviet201.cinema.payment.vnpay.code.ResponseCodeVNPAY;
import com.nongviet201.cinema.payment.vnpay.service.VnPayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final VnPayService vnPayService;
    private final BillRepository billRepository;
    private final TranslationPaymentRepository translationPaymentRepository;
    private final UserService userService;
    private final ReservationService reservationService;
    private final BillSeatService billSeatService;
    private final ReservationRepository reservationRepository;

    @Override
    public String transitionToPaymentMethod(
        ToPaymentRequest request
    ) {
        if (request.getPaymentMethod() == PaymentMethod.VNPAY) {
            return vnPayService.createPayment(
                request.getBillId(),
                request.getAmount(),
                request.getTimeRemain()
            );
        } else if (request.getPaymentMethod() == PaymentMethod.MOMO) {
            return null;
        } else if (request.getPaymentMethod() == PaymentMethod.ZALOPAY) {
            return null;
        }

        throw new BadRequestException("Người dùng chưa chọn phuơng thức thanh toán");
    }

    @Override
    public Integer PaymentVnPayReturnCheck(
        VnPayReturnRequest request
    ) {
        Bill bill = billRepository.findById(request.getBillId())
            .orElseThrow(() -> new BadRequestException("Không tìm thấy thông tin hóa đơn"));

        if (bill.getStatus() != BillStatus.PENDING_PAYMENT) {
            throw new BadRequestException("Đơn hàng đã được thanh toán hoặc đang trong quá trình xử lý");
        }

        TranslationPayment translationPayment = bill.getTranslationPayment();
        translationPayment.setBankCode(request.getBankCode());
        translationPayment.setTransactionNo(request.getTransactionNo());
        translationPayment.setPayDate(request.getPayDate());

        checkVnPayResponseCode(
            bill,
            translationPayment,
            request.getAmount(),
            ResponseCodeVNPAY.fromCode(request.getResponseCode())
        );

        return bill.getId();
    }

    private void updateBill(
        Bill bill,
        TranslationPayment translationPayment,
        ResponseCodeVNPAY responseCode,
        boolean status,
        BillStatus billStatus
    ) {
        translationPayment.setResponseCodeVNPAY(responseCode);
        translationPayment.setStatus(status);
        translationPayment.setUpdatedAt(now());
        translationPaymentRepository.save(translationPayment);

        bill.setStatus(billStatus);
        bill.setUpdatedAt(now());
        bill.setTranslationPayment(translationPayment);
        billRepository.save(bill);
    }

    private void billSuccess(
        int billId,
        int userId,
        int showtimeId
    ) {
        billSeatService.getBillSeatByBillId(billId).forEach(
            billSeat -> {
                updateReservation(
                    userId,
                    showtimeId,
                    billSeat.getSeat().getId()
                );
            });
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
        TranslationPayment translationPayment,
        long requestAmount,
        ResponseCodeVNPAY responseCode
    ) {
        if (responseCode == ResponseCodeVNPAY.PAYMENT_CANCEL) {
            updateBill(bill, translationPayment, responseCode, false, BillStatus.CANCEL);
        }
        if (responseCode != ResponseCodeVNPAY.PAYMENT_SUCCESS) {
            updateBill(bill, translationPayment, responseCode, false, BillStatus.FAILED);
        } else {
            // kiểm tra nếu thanh toán thành công nhưng số tiền không khớp nhau thì gán lỗi
            if ((requestAmount / 100) != bill.getTotalPrice()) {
                translationPayment.setResponseCodeVNPAY(ResponseCodeVNPAY.PAYMENT_ERROR_AMOUNT);
                updateBill(bill, translationPayment, responseCode, false, BillStatus.PENDING_PROCESSING);
            }
            // kiểm tra nếu thanh toán thành công nhưng user thanh toán không phải user hiện tại thì gán lỗi
            else if (!bill.getUser().getId().equals(userService.getCurrentUser().getId())) {
                translationPayment.setResponseCodeVNPAY(ResponseCodeVNPAY.PAYMENT_ERROR_USER);
                updateBill(bill, translationPayment, responseCode, false, BillStatus.PENDING_PROCESSING);
            }
            // nếu không có lỗi gì thì cho bill thành công
            else {
                updateBill(bill, translationPayment, responseCode, true, BillStatus.PAID);
                billSuccess(bill.getId(), bill.getUser().getId(), bill.getShowtime().getId());
            }
        }
    }
}