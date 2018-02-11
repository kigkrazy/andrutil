package com.reizx.andrutil;

import android.content.Context;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Assets 读写工具类
 * Created by kig on 2017/12/19.
 */

public class AssetsUtil {
    /**
     * 读access中文件的内容到字符串
     * @param context
     * @param name
     * @return
     */
    public static String readFile2String(Context context, String name) {
        String ret = "" ;
        try {
            InputStream in = context.getResources().getAssets().open(name);
            ret = IOUtils.toString(in, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 读access中文件的内容到byte[]数组
     * @param context
     * @param name
     * @return
     */
    public static byte[] readFile2ByteArray(Context context, String name) {
        byte[] ret = null;
        try {
            InputStream in = context.getResources().getAssets().open(name);
            ret = IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 将asset中的文件复制到指定位置
     * @param context
     * @param name assets文件名
     * @param path 目标文件路径
     * @return 写入文件成功返回true，否则返回false
     */
    public static boolean read2File(Context context, String name, String path) {
        boolean ret = false;
        try {
            InputStream in = context.getResources().getAssets().open(name);
            byte[] buf = IOUtils.toByteArray(in);
            FileUtils.writeByteArrayToFile(new File(path), buf);
            ret = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 将asset中的文件复制到指定位置
     * @param context
     * @param name
     * @param path
     * @return
     */
    public static boolean copy2File(Context context, String name, String path) {
        return read2File(context, name, path);
    }


    /**
     * 打开assets中的文件
     * @param context
     * @param name
     * @return
     */
    public static InputStream openAssetsFile(Context context, String name) {
        InputStream ret = null;
        try {
            ret = context.getResources().getAssets().open(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * check asset exists
     * 检测文件是否存在
     * @param context
     * @param assetFilePath
     * @return
     */
    public static boolean exists(final Context context, final String assetFilePath) {
        boolean exist = false;
        try {
            InputStream stream = context.getAssets().open(assetFilePath);
            stream.close();
            exist = true;
        } catch (Exception e) {
        }
        return exist;
    }
}
