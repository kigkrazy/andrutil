package com.reizx.andrutil;

import android.content.Context;
import android.content.res.AssetManager;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ResourceUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 资源文件拷贝扩展
 */
public class ResourceExtUtils {
    /**
     * @param context
     * @param assetsDirPath
     * @param destDirPath
     * @return
     */
    public static boolean copyDirFromAssets(final Context context, final String assetsDirPath, final String destDirPath) {
        try {
            AssetManager assetManager = context.getAssets();
            List<String> files = Arrays.asList(assetManager.list(assetsDirPath)); //使用这个来判断是否是文件夹

            if (files.size() > 0) {
                if (!FileUtils.createOrExistsDir(destDirPath)) {
                    return false;
                }
                for (String file : files) {
                    String assetsPath = assetsDirPath + File.separator + file;
                    String destPath = destDirPath + File.separator + file;
                    copyDirFromAssets(context, assetsPath, destPath);
                }
            } else {
                ResourceUtils.copyFileFromAssets(assetsDirPath, destDirPath);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
