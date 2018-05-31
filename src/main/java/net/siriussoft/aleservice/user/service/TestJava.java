package net.siriussoft.aleservice.user.service;

import net.siriussoft.aleservice.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJava {
    public static void main(String[] args) {

        TestJava tj = new TestJava();
        //checkPCTNo();
        String yearStr = "2018";
        for (int monthIdx = 1; monthIdx < 13; monthIdx++) {
            String dealYmd = yearStr + StringUtils.leftPad("" + monthIdx, 2, "0");
            if (tj.isOnGoingDealYmd(dealYmd)) {
                System.out.println(dealYmd + "   true");
            } else {
                System.out.println(dealYmd + "   false");
            }
        }
    }

    public boolean isOnGoingDealYmd(String dealYmd) {
        boolean result = false;
        String parsePatterns = "yyyyMMdd";
        Date dealDay = DateUtil.getMonthWithFirstDay(dealYmd, parsePatterns);

        Date today = DateUtil.getCurrentDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, -70);

        SimpleDateFormat mmFormat = new SimpleDateFormat("yyyyMM");

        Date onGoingDate = DateUtil.getMonthWithFirstDay(mmFormat.format(cal.getTime()), parsePatterns);

        SimpleDateFormat format = new SimpleDateFormat(parsePatterns);
        System.out.println("today => " + format.format(dealDay) + " onGoingDate   " + format.format(onGoingDate) + " compare ");

        if (dealDay.compareTo(onGoingDate) >= 0) {
            result = true;
        }

        return result;
    }

    public static void checkPCTNo() {
        String pctNo1 = "PCT/US2004/038580";
        String pctNo2 = "PCT/US04/038580";
        String pctNo3 = "PCTUS200438580";
        String pctNo4 = "PCT/US97/38580";
        String pctNo5 = "US2004/038580";


        System.out.println("pctNo 1 = " + pctNo1 + "  check no = " + checkPctNo(pctNo1));
        System.out.println("pctNo 2 = " + pctNo2 + "  check no = " + checkPctNo(pctNo2));
        System.out.println("pctNo 3 = " + pctNo3 + "  check no = " + checkPctNo(pctNo3));
        System.out.println("pctNo 4 = " + pctNo4 + "  check no = " + checkPctNo(pctNo4));
        System.out.println("pctNo 5 = " + pctNo5 + "  check no = " + checkPctNo(pctNo5));
    }
    public static String checkPctNo (String pctNo) {
        String result = pctNo;
        String countryYear = "";
        String appNo = "";
        boolean doProcess = false;

        if (StringUtils.isNotEmpty(pctNo)) {
            String pct[] = pctNo.split("/");
            if (pct.length == 3) {
                countryYear = pct[1];
                appNo = pct[2];
                doProcess = true;
            } else if (pct.length == 2) {
                countryYear = pct[0];
                appNo = pct[1];
                doProcess = true;
            }
        }

        if (doProcess) {
            String countryCode = "";
            String yearCode = "";
            String strPattern = "[a-zA-Z]";
            String numPattern = "[0-9]";

            countryCode = getPatternString(countryYear, strPattern);
            yearCode = getPatternString(countryYear, numPattern);

            if (yearCode.length() == 2) {
                try {
                    int yearNum = Integer.parseInt(yearCode);

                    if (yearNum > 50) {
                        yearCode = "19"+yearCode;
                    } else {
                        yearCode = "20"+yearCode;
                    }
                } catch (Exception e) {
                    yearCode = "20"+yearCode;
                }
            }

            System.out.println(countryCode);
            System.out.println(yearCode);
            countryYear = countryCode+yearCode;
            if (appNo.length() < 6) {
                appNo = StringUtils.leftPad(appNo, 6, "0");
            }
            result = countryYear+appNo;
        }

        return result;
    }

    public static String getPatternString(String input, String patternStr) {
        String result = "";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()) {
            result += matcher.group(0);
        }

        return result;
    }
}
