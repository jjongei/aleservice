package net.siriussoft.aleservice.common.util;

import net.siriussoft.aleservice.common.exception.APIResultCodeException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

public class JsonUtil {
    public String PHARM_URL = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade";
    public String KEY = "NyyLrBwoBj6XTfBAeGqlh9lmDeK4cK3ufqxfK7PHNcYRDx5tM1di6UmWDGWWBQc%2F%2BJcsyJ5Ev2D2mr6YNkf8gw%3D%3D";

    public JsonUtil() {
    }
    public JsonUtil(String url, String key) {
        this.PHARM_URL = url;
        this.KEY = key;
    }

    private String getURLParam(String lawdCd, String dealYmd){
        String url = PHARM_URL+"?_type=json&LAWD_CD="+lawdCd+"&DEAL_YMD="+dealYmd+"&serviceKey="+KEY;

        return url;
    }
    private String getURLParam(String pageNo, String numOfRows, String lawdCd, String dealYmd){
        String url = PHARM_URL+"?_type=json&pageNo="+pageNo+"&numOfRows="+ numOfRows +"&LAWD_CD="+lawdCd+"&DEAL_YMD="+dealYmd+"&serviceKey="+KEY;
        //String url = PHARM_URL+"?_type=json&LAWD_CD="+lawdCd+"&DEAL_YMD="+dealYmd+"&serviceKey="+KEY;

        return url;
    }

    public JSONObject readJsonFromUrl(String lawdCd, String dealYmd) throws IOException, JSONException, APIResultCodeException {
        String url = this.getURLParam(lawdCd, dealYmd);

        InputStream is = new URL(url).openStream();
        JSONObject json = null;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            json = new JSONObject(jsonText);
            JSONObject headerObject = this.getJsonObjectByKey(json, "header");

            String resultCode = ""+headerObject.get("resultCode");
            String resultMsg = "" + headerObject.get("resultMsg");

            if(resultCode.equals("99")) {
                throw new APIResultCodeException(resultMsg);
            }
            System.out.println(json.toString());
        } finally {
            is.close();
        }
        return json;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    public JSONObject getJsonObjectByKey(JSONObject json, String targetKey) {
        JSONObject result = null;
        Iterator<?> keys = json.keys();
        while( keys.hasNext() ) {
            String jsonKey = (String)keys.next();
            if ( json.get(jsonKey) instanceof JSONObject ) {
                JSONObject tmpObject = (JSONObject) json.get(jsonKey);
                if (targetKey.equals(jsonKey)) {
                    result = tmpObject;
                } else {
                    result = getJsonObjectByKey(tmpObject, targetKey);
                }
                if (!ObjectUtils.isEmpty(result)) {
                    break;
                }
            }
        }
        return result;
    }
}
