package com.reizx.andrutil;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HttpUrlUtil的单元测试用例
 * Created by kig on 2018/4/18.
 */

public class HttpUrlUtilTest {
    public final static Pattern HTTP_PATTERN = Pattern.compile("^(http://|https://)(.*?)(/.*)\\?(.*)");
    public final static String TEST_URL = "https://www.baidu.com/port/packages/?nt=WIFI&cfrom=2&model=Coolpad+8730L&density=3.0&screensize=1080_1776";

    @Test
    public void RegexTest() {
        System.out.printf("matches...");
        System.out.printf("the host : " + HttpUrlUtil.getHost(TEST_URL) + "\n");
        System.out.printf("the path : " +  HttpUrlUtil.getPath(TEST_URL) + "\n");
        System.out.printf("the param : " +  HttpUrlUtil.getParamString(TEST_URL) + "\n");
    }
}
