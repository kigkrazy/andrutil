package com.reizx.andrutil;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Http地址解析
 * Created by kig on 2018/4/18.
 */

public class HttpUrlUtil {
    /**
     * Matcher.group(2) -> host 域名
     * Matcher.group(3) -> path 地址
     * Matcher.group(4) -> param 参数
     */
    public final static Pattern pattern = Pattern.compile("^(http://|https://)(.*?)(/.*)\\?(.*)");
    /**
     * 获取请求的参数map
     * @param url
     * @return
     */
    public static HashMap<String, String> getParamMaps(String url){
        HashMap<String, String> paramMaps = new HashMap<>();
        String paramString = getParamString(url);
        if (StringUtils.isEmpty(paramString)){
            return paramMaps;
        }

        String[] paramList = paramString.split("&");
        for (String paramSub : paramList){
            String[] paramSubKV = paramSub.split("=");
            paramMaps.put(paramSubKV[0], paramSubKV[1]);
        }
        return paramMaps;
    }


    /**
     * 获取请求地址
     * @param url
     * @return
     */
    public static String getParamString(String url){
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()){
            return matcher.group(4);
        }
        return null;
    }

    /**
     * 获取请求地址
     * @param url
     * @return
     */
    public static String getPath(String url){
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()){
            return matcher.group(3);
        }
        return null;
    }

    /**
     * 获取请求Host
     * @param url
     * @return
     */
    public static String getHost(String url){
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()){
            return matcher.group(2);
        }
        return null;
    }
}
