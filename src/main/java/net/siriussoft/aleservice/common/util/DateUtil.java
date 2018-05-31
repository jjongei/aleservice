package net.siriussoft.aleservice.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String parsePatterns ="yyyy.MM.dd";

    /**
     * <p>
     * 현재 시간을 기준으로 Date 객체를 반환한다.
     * </p>
     *
     * @return 현재 시간의 Date 객체
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * <p>
     * 두개의 날짜 범위 객체를 검사하여 유효한 날짜인지를 확인한 결과를 반환한다.
     * </p>
     * <p>
     * 다음의 항목을 검사한다.
     * </p>
     * <ul>
     * 	<li>시작일과 종료일 Date 객체가 null 인지 여부를 검사</li>
     * 	<li>시작일이 종료일 보다 이전인지를 검사</li>
     * 	<li>종료일이 시작일보다 이후인지를 검사</li>
     * </ul>
     * @param fromDate
     * @param toDate
     * @return
     * @throws IllegalArgumentException 입력 값이 없으면 발생한다.
     */
    public static boolean isValidDateRange(Date fromDate, Date toDate) throws IllegalArgumentException {
        boolean result = false;

        if(fromDate == null){
            throw new IllegalArgumentException("From Date");
        }
        if(toDate == null){
            throw new IllegalArgumentException("To Date");
        }

        return fromDate.compareTo(toDate) <= 0 && toDate.compareTo(fromDate) >= 0;
    }


    /**
     * <p>
     * 날짜 범위 검색을 위해, 시작 날짜의 시간을 초기화 한다.
     * </p>
     * <code>
     * ex) 2016.09.13 15:43:21 --> 2016.09.13 00:00:00
     * </code>
     *
     * @param fromDate
     * @return
     */
    public static Date initDateByFrom(Date fromDate) {
        if (fromDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fromDate);

            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);

            fromDate = calendar.getTime();
        }

        return fromDate;
    }

    /**
     * <p>
     * 날짜 범위 검색을 위해, 종료 날짜의 시간을 초기화 한다.
     * </p>
     * <code>
     * ex) 2016.09.13 15:43:21 --> 2016.09.13 23:59:59
     * </code>
     *
     * @param toDate
     * @return
     */
    public static Date initDateByTo(Date toDate) {
        if (toDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(toDate);

            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.HOUR_OF_DAY, 23);

            toDate = calendar.getTime();
        }

        return toDate;
    }
    public static Date parseDate(final String str){
        return parseDate(str, parsePatterns);
    }

    public static Date parseDate(final String str, final String... parsePatterns){
        try {
            return DateUtils.parseDate(str, parsePatterns);
        } catch (Exception e) {
            return null;
        }
    }
    public static String format(final Date date){
        return format(date, parsePatterns);
    }
    public static String format(final Date date, final String parsePatterns){
        try {
            return new SimpleDateFormat(parsePatterns).format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static Date getMonthWithFirstDay(String yyyyMM, String parsePatterns) {
        Date result = null;
        if(StringUtils.isNotEmpty(yyyyMM)) {
            result = DateUtil.parseDate(yyyyMM+"01", parsePatterns);
        }
        return result;
    }
}
