/*
 * 加载声明入口，主要用于so加载时候，动态注册函数。
 * create by kig on 2017-10-09 14:17:26
 */

#include <stdio.h>
#include "jni.h"
#include <string>
#include <string.h>

#include "utils/include/log.h"
#include "../include/jni_export.h"

using namespace std;

// region 函数声明
void RegisterMethod(JNIEnv *env, const string &jClazzName, JNINativeMethod jniNativeMethod[]);
void UnRegisterMethod(JNIEnv *env, const string &jClazzName);
// endregion

// region 定义全局变量
#define JNI_NavClass "com/reizx/xncore/xnative/XnCoreNative" //JNI的类的名称
JNINativeMethod g_nativeMethod[]= {//JNI类中包含的函数名
        {"hook","()Ljava/lang/String;",(void*)Hook},
        {"unhook","()Ljava/lang/String;",(void*)Unhook},
        {"testHook","(Ljava/lang/String;)Ljava/lang/String;",(void*)TestHook}
};
// endregion

/**
 * SO加载时自动调用
 * @param vm
 * @param reserved
 * @return
 */
jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    LOGTD("xncore", "xncore start load...");
    JNIEnv *env;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK)
        return JNI_ERR;
    RegisterMethod(env, JNI_NavClass,  g_nativeMethod);
    return JNI_VERSION_1_6;
}

/**
 * JNI 卸载函数
 * @param vm
 * @param reserved
 */
void JNI_OnUnload(JavaVM* vm, void* reserved) {
    JNIEnv *env;
    int nJNIVersionOK = vm->GetEnv((void **)&env, JNI_VERSION_1_6) ;
    UnRegisterMethod(env, JNI_NavClass);
}

/**
 * 注册函数
 * @param env JVM环境
 * @param jClazzName 类名
 * @param jniNativeMethod 函数结构数组
 */
void RegisterMethod(JNIEnv *env, const string &jClazzName, JNINativeMethod jniNativeMethod[]) {
    LOGTD("xcore:xncore","RegisterNatives the class name : %s", jClazzName.c_str());
    jclass jClazz = env->FindClass(jClazzName.c_str());
    if (jClazz == NULL) {
        LOGTE("xcore:xncore","FindClass Failed !");
    }

    if (env->RegisterNatives(jClazz, g_nativeMethod, sizeof(g_nativeMethod)/ sizeof(g_nativeMethod[0])) < 0) {
        LOGTE("xcore:xncore","RegisterNatives Failed !");
    }
    env->DeleteLocalRef(jClazz);
}

void UnRegisterMethod(JNIEnv *env, const string &jClazzName) {
    LOGTD("xcore:xncore","UnRegisterMethod the class name : %s", jClazzName.c_str());
    jclass jClazz = env->FindClass(jClazzName.c_str());
    env->UnregisterNatives(jClazz);
    env->DeleteLocalRef(jClazz);
}