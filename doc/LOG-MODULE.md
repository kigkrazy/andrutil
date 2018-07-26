# log模块(LoggerConfigeration)的使用方法
1. 在程序开始时候初始化
```
try {
    //初始化logcat模板
    //一般来说我们只设置name就可以，其余的保持默认
    LogcatAppender logcatAppender = LogcatAppenderBuilder
            .newBuilder()
            .name("logcat")//名称
            .pattern("%-5relative [%thread][%file:%M:%line] - %msg%n")//日志输出模板
            .build();

    //初始化文件日志写入模板
    //一般来说我们只设置name跟filePattern就可以，其余的保持默认
    FileAppender fileAppender = RollingFileAppenderBuilder
            .newBuilder()
            .name("filelog")//名称，默认为“andrutil_rolling_file”
            .filePattern("/sdcard/andrutil/andrutil.%d{yyyy-MM-dd}.log")//日志文件路径
            .history(5)//最大日志保存天数，默认为 5
            .logPattern("%-5relative [%thread][%file:%M:%line] - %msg%n")//log打印模板
            .maxFileSize("10MB")//最大文件大小，文件大的话会在新文件写入默认为"10MB"
            .append(true)//是否使用安全模式写入，默认TRUE。多个线程开启写入同一个文件保证多线程安全
            .build();

    LoggerConfigeration.newConfigeration()
            .addAppend(logcatAppender)//添加模板
            .addAppend(fileAppender)//添加文件打印模块，如果不需要可以删除
            .config();
} catch (Exception e) {
    Log.d("logback-init", "logback init error : " + e);
    e.printStackTrace();
}
```

2. 使用
```
Logger logger = LoggerConfigeration.getLogger("andrutl-tag");

/*以下日志只会在控制台输出*/
logger.trace("angcyo-->{}", "trace");
logger.debug("angcyo-->{}", "debug");
logger.info("angcyo-->{}", "info");
logger.warn("angcyo-->{}", "warn");
logger.error("angcyo-->{}", "error");
```



