package com.reizx.andrutil;

import android.os.Build;

import com.blankj.utilcode.util.ShellUtils;

/**
 * 一些系统命令
 * Created by kig on 2017/12/18.
 */

public class SysShell {
    /**
     * 安卓6.0以后需要动态授权，不方便在App里面写的话，可以用命令行写
     * @param pkgName
     * @param perm
     */
    public static void RequestPermission(String pkgName, String perm){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //命令行请求授权
            ShellUtils.execCmd("pm grant " + pkgName + " " + perm, true);
        }
    }
}
