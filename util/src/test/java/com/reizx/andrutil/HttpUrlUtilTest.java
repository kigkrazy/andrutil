package com.reizx.andrutil;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kig on 2018/4/18.
 */

public class HttpUrlUtilTest {
    public final static Pattern HTTP_PATTERN = Pattern.compile("^(http://|https://)(.*?)(/.*)\\?(.*)");
    public final static String TEST_URL = "https://www.baidu.com/port/packages/?nt=WIFI&cfrom=2&model=Coolpad+8730L&density=3.0&screensize=1080_1776";

    @Test
    public void RegexTest() {
        Matcher matcher = HTTP_PATTERN.matcher(TEST_URL);

        if (matcher.matches()){
            System.out.printf("matches...");
            System.out.printf("the host : " + matcher.group(2) + "\n");
            System.out.printf("the path : " + matcher.group(3) + "\n");
            System.out.printf("the param : " + matcher.group(4) + "\n");
            return;
        }
        System.out.printf("no matches...");
    }
}
