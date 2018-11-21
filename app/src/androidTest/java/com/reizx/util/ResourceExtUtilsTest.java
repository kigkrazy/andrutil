package com.reizx.util;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.reizx.andrutil.ResourceExtUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class ResourceExtUtilsTest {
    @Test
    public void test() throws IOException {
        Application app = (Application) InstrumentationRegistry.getTargetContext().getApplicationContext();
        boolean result = ResourceExtUtils.copyDirFromAssets(app, "resource_util_test", "/sdcard/resource_util_test");
        Log.d("ResourceExtUtilsTest", "the result : " + result);
    }
}
