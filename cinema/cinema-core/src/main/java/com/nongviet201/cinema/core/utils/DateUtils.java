package com.nongviet201.cinema.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateUtils {

    /**
     * Lấy ngày giờ hiện tại
     *
     * @return LocalDateTime hiện tại
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Lấy ngày hiện tại
     *
     * @return LocalDate hiện tại
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Lấy tuần hiện tại của tháng
     *
     * @return Số tuần của tháng hiện tại
     */
    public static int getCurrentWeekOfMonth() {
        LocalDate today = getCurrentDate();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return today.get(weekFields.weekOfMonth());
    }

    /**
     * Lấy tháng hiện tại của năm
     *
     * @return Tháng hiện tại (dạng số từ 1 đến 12)
     */
    public static int getCurrentMonth() {
        return getCurrentDate().getMonthValue();
    }

    /**
     * Lấy năm hiện tại
     *
     * @return Năm hiện tại
     */
    public static int getCurrentYear() {
        return getCurrentDate().getYear();
    }

    /**
     * Kiểm tra xem một ngày có phải là ngày cuối tuần không
     *
     * @param date Ngày cần kiểm tra
     * @return true nếu là ngày cuối tuần, false nếu không
     */
    public static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6; // 6: Saturday, 7: Sunday
    }

    /**
     * Tính số ngày giữa hai ngày
     *
     * @param start Ngày bắt đầu
     * @param end   Ngày kết thúc
     * @return Số ngày giữa hai ngày
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Lấy số tuần giữa hai ngày
     *
     * @param start Ngày bắt đầu
     * @param end   Ngày kết thúc
     * @return Số tuần giữa hai ngày
     */
    public static long weeksBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.WEEKS.between(start, end);
    }

    /**
     * Lấy giờ hiện tại
     *
     * @return LocalTime hiện tại
     */
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    /**
     * Lấy ngày đầu tiên của tháng hiện tại
     *
     * @return LocalDate ngày đầu tiên của tháng hiện tại
     */
    public static LocalDate getFirstDayOfCurrentMonth() {
        return getCurrentDate().withDayOfMonth(1);
    }

    /**
     * Lấy ngày cuối cùng của tháng hiện tại
     *
     * @return LocalDate ngày cuối cùng của tháng hiện tại
     */
    public static LocalDate getLastDayOfCurrentMonth() {
        return getCurrentDate().withDayOfMonth(getCurrentDate().lengthOfMonth());
    }

    /**
     * Tính số tuần của năm hiện tại
     *
     * @return Số tuần của năm hiện tại
     */
    public static int getWeeksInCurrentYear() {
        LocalDate startOfYear = LocalDate.of(getCurrentYear(), 1, 1);
        LocalDate endOfYear = LocalDate.of(getCurrentYear(), 12, 31);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int startWeek = startOfYear.get(weekFields.weekOfYear());
        int endWeek = endOfYear.get(weekFields.weekOfYear());
        return endWeek - startWeek + 1;
    }

}