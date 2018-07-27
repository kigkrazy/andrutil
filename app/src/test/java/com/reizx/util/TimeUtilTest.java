package com.reizx.util;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeUtilTest {
    @Test
    public void test(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String date1 = "2018-07-27";
        String date2 = "2018-07-21";
        boolean isDate = isDate(sdf, "--2018-07-2711");
        long span = TimeUtils.getTimeSpan(date1, date2 , sdf, TimeConstants.DAY);
    }

    public boolean isDate(SimpleDateFormat sdf, String date){
        try{
            sdf.parse(date);
            return true;
        }catch(Exception e){
            //如果不能转换,肯定是错误格式
            return false;
        }
    }
}
