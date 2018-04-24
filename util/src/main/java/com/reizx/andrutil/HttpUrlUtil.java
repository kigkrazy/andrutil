package com.reizx.andrutil;

import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
    public static HashMap<String, String> getParamMaps(String url, boolean isUrlDecode){
        HashMap<String, String> paramMaps = new HashMap<>();
        String paramString = getParamString(url);
        if (StringUtils.isEmpty(paramString)){
            return paramMaps;
        }
        return form2Map(paramString, isUrlDecode);
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

    /**
     * form表单数据转为map
     * @param formString 表单数据
     * @param isUrlDecode 是否对数据进行url解密
     * @return
     */
    public static HashMap<String, String> form2Map(String formString, boolean isUrlDecode) {
        try {
            HashMap<String, String> paramMaps = new HashMap<>();

            String[] paramList = formString.split("&");
            for (String paramEl : paramList){
                if (StringUtils.isEmpty(paramEl))
                    continue;

                String[] paramSubKV = paramEl.split("=");
                String key = paramSubKV[0];
                String val = paramSubKV[1];
                if (isUrlDecode) {
                    key = URLDecoder.decode(key, "utf-8");
                    val = URLDecoder.decode(val, "utf-8");
                }
                paramMaps.put(key, val);
            }
            return paramMaps;
        }catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }


    /**
     * map转为form数据
     * @param paramMaps 数据map
     * @param isUrlEncode 是否对数据进行url编码加密
     * @return
     */
    public static String map2Form(HashMap<String, String> paramMaps, boolean isUrlEncode) {
        try {
            String rel = "";
            Iterator<Map.Entry<String, String>> iter = paramMaps.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = iter.next();

                String key = entry.getKey();
                String val = entry.getValue();

                if (isUrlEncode) {
                    key = URLEncoder.encode(key, "utf-8");
                    val = URLEncoder.encode(val, "utf-8");
                }
                rel += key + "=" + val + "&";
            }
            if (!rel.equals(""))
                rel = rel.substring(0, rel.length() - 1);
            return rel;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
