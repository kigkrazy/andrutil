LOCAL_PATH := $(call my-dir)

#编译exec模块，这是一个用来执行shell的函数
include $(CLEAR_VARS)
LOCAL_MODULE:= andrutil_exec
LOCAL_SRC_FILES:= $(wildcard $(LOCAL_PATH)/src/exec/*.cpp)
LOCAL_LDLIBS := -ldl -llog
#LOCAL_CFLAGS += -std=c++11 #增加C++11支持
include $(BUILD_SHARED_LIBRARY)


