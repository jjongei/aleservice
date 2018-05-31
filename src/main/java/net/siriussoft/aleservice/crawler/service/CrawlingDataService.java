package net.siriussoft.aleservice.crawler.service;

import net.siriussoft.aleservice.common.exception.APIResultCodeException;
import net.siriussoft.aleservice.common.util.DateUtil;
import net.siriussoft.aleservice.common.util.JsonUtil;
import net.siriussoft.aleservice.crawler.model.ApartmentTransaction;
import net.siriussoft.aleservice.crawler.model.ApartmentTransactionHistory;
import net.siriussoft.aleservice.crawler.model.LawdCode;
import net.siriussoft.aleservice.crawler.repository.LawdCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CrawlingDataService {
    @Autowired
    LawdCodeRepository lawdCodeRepository;

    @Autowired
    ApartmentTransactionService apartmentTransactionService;

    @Autowired
    ApartmentTransactionHistoryService apartmentTransactionHistoryService;

    private int duration = -70;

    private boolean isOnGoingDealYmd(String dealYmd) {
        boolean result = false;
        String parsePatterns = "yyyyMMdd";
        Date dealDay = DateUtil.getMonthWithFirstDay(dealYmd, parsePatterns);

        Date today = DateUtil.getCurrentDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, duration);

        SimpleDateFormat mmFormat = new SimpleDateFormat("yyyyMM");
        Date onGoingDate = DateUtil.getMonthWithFirstDay(mmFormat.format(cal.getTime()), parsePatterns);

        SimpleDateFormat format = new SimpleDateFormat(parsePatterns);
        //System.out.println("today => " + format.format(dealDay) + " onGoingDate   " + format.format(onGoingDate) + " compare ");

        if (dealDay.compareTo(onGoingDate) >= 0) {
            result = true;
        }

        return result;
    }

    public void makeApartmentTransactionData() {
        String[] yearArr = {"2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"};

        List<LawdCode> lawdCodeList = lawdCodeRepository.getValidLawdCodeList();

        outerLoop:
        for (int lawdCodeIdx = 0; lawdCodeIdx < lawdCodeList.size(); lawdCodeIdx++) {
            String lawdCode = lawdCodeList.get(lawdCodeIdx).getLawdCd();
            for(int yearIdx = 0; yearIdx < yearArr.length; yearIdx++) {
                String yearStr = yearArr[yearIdx];
                for(int monthIdx = 1; monthIdx < 13; monthIdx++) {
                    String monthStr = StringUtils.leftPad(""+monthIdx, 2, "0");
                    String dealYmd = yearStr+monthStr;
                    // System.out.println(lawdCode + "  " + dealYmd);
                    ApartmentTransactionHistory apartmentTransactionHistory = new ApartmentTransactionHistory(lawdCode, dealYmd);
                    apartmentTransactionHistory = apartmentTransactionHistoryService.findPreviousTransaction(apartmentTransactionHistory);

                    boolean forDeleteData = this.isOnGoingDealYmd(dealYmd);

                    if(forDeleteData) {
                        apartmentTransactionService.removeForUpdateTransaction(lawdCode, yearStr, monthStr);
                    }

                    if ("F".equals(apartmentTransactionHistory.getIsComplete()) || forDeleteData) {
                        apartmentTransactionHistoryService.persistDataWithCommit(apartmentTransactionHistory);
                        try {
                            apartmentTransactionHistory = insertApartmentTransactionWithJson (apartmentTransactionHistory);
                        } catch(APIResultCodeException e) {
                            System.out.println(e.getMessage());
                            break outerLoop;
                        } catch(IOException ioe) {
                            System.out.println(ioe.getMessage());
                        } catch(NullPointerException ne) {
                            System.out.println(ne.getMessage());
                        }
                        apartmentTransactionHistoryService.update(apartmentTransactionHistory);
                    }
                }
            }
        }
    }

    public ApartmentTransactionHistory insertApartmentTransactionWithJson(ApartmentTransactionHistory apartmentTransactionHistory) throws APIResultCodeException, IOException, NullPointerException {

        if (apartmentTransactionHistory != null && StringUtils.isNotEmpty(apartmentTransactionHistory.getLawdCd())
                && StringUtils.isNotEmpty(apartmentTransactionHistory.getDealYmd())) {
            JsonUtil jsonUtil = new JsonUtil();
            int count = 0;
            String lawdCode = apartmentTransactionHistory.getLawdCd();
            String dealYmd = apartmentTransactionHistory.getDealYmd();
            JSONObject json = jsonUtil.readJsonFromUrl(lawdCode, dealYmd);
            JSONObject headerObject = jsonUtil.getJsonObjectByKey(json, "header");
            JSONObject bodyObject = jsonUtil.getJsonObjectByKey(json, "body");
            JSONObject itemsObject = jsonUtil.getJsonObjectByKey(json, "items");

            JSONArray items = null;
            items = (JSONArray) itemsObject.get("item");
            Integer pageNo = (Integer)bodyObject.get("pageNo");
            Integer totalCount = (Integer)bodyObject.get("totalCount");
            Integer numOfRows = (Integer)bodyObject.get("numOfRows");

            System.out.println("total count => " + totalCount + "   selected item count =>  " + items.length());

            apartmentTransactionHistory.setTotalCount(totalCount);
            for(int idx = 0; idx < items.length(); idx++) {
                JSONObject item = (JSONObject) items.get(idx);
                String amount = StringUtils.trim(""+item.get("거래금액"));
                String aptBuiltYear = StringUtils.trim(""+item.get("건축년도"));
                String transactionYear = StringUtils.trim(""+item.get("년"));
                String dongName = StringUtils.trim(( String) item.get("법정동"));
                String aptName = StringUtils.trim((String) item.get("아파트"));
                String transactionMonth = StringUtils.trim(""+item.get("월"));
                String transactionDay = StringUtils.trim(""+item.get("일"));
                String area = StringUtils.trim(""+item.get("전용면적"));
                String jibun = StringUtils.trim(""+item.get("지번"));
                String floor = StringUtils.trim(""+item.get("층"));
                String aptKey = getConvertSHA256(lawdCode+dongName+jibun);
                //String aptTrKey = getConvertSHA256(lawdCode+dongName+jibun+transactionYear+transactionMonth+transactionDay+area+floor);
                String ckeckAptTrKey = getConvertSHA256(lawdCode+dongName+jibun+transactionYear+transactionMonth);


                ApartmentTransaction apartmentTransaction = new ApartmentTransaction();
                apartmentTransaction.setAptKey(aptKey);
                apartmentTransaction.setAptArea(area);
                apartmentTransaction.setTransactionYear(transactionYear);
                apartmentTransaction.setTransactionMonth(transactionMonth);
                apartmentTransaction.setTransactionDay(transactionDay);
                apartmentTransaction.setTransactionAmount(amount);
                apartmentTransaction.setAptArea(area);
                apartmentTransaction.setLawdCd(lawdCode);
                apartmentTransaction.setDongName(dongName);
                apartmentTransaction.setJibun(jibun);
                apartmentTransaction.setAptName(aptName);
                apartmentTransaction.setAptBuiltYear(aptBuiltYear);
                apartmentTransaction.setFloor(floor);

                apartmentTransactionService.save(apartmentTransaction);

                count ++;
                //System.out.println("   idx =" +idx + " " +item.get("지번") + "   " +item.get("지역코드") + " " + item.get("아파트") + "  " +item.get("전용면적") + " " + item.get("거래금액")+ item.get("일"));
            }
            apartmentTransactionService.flush();

            apartmentTransactionHistory.setInsertCount(count);
            apartmentTransactionHistory.setIsComplete("T");
            apartmentTransactionHistory.setEndDate(DateUtil.getCurrentDate());

            System.out.println("Success count ==> " + count);
        }

        return apartmentTransactionHistory;
    }

    public String getConvertSHA256(String str){
        String SHA = "";
        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();

            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }
}
