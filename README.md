# Android工具类库
囊括了一大部分Android应用开发过程当中常用的工具类。工具类来源整理自网络和自己编写。  
每个类都会在开头提供用例。

# 使用
**建议排除一些不用的库，避免引入过多**：  
```
api('com.reizx:andrutil:1.9.3'){
    //retrofit2 相关
    //exclude group: 'com.squareup.retrofit2'
    //rxjava2相关的库
    //exclude group: 'io.reactivex.rxjava2'
    //okhttp3相关
    //exclude group: 'com.squareup.okhttp3'
    //exclude group: 'com.squareup.okio'
    //一个运行时HOOK库
    exclude group: 'me.weishu:epic'
    //一个通过扫描"/proc"文件夹获取安卓所有进程的库（5.x之后停用）
    exclude group: 'com.jaredrummler'
    
    //okhttputils是一个安卓上方便HTTP请求的工具类，作为retrofit的一个应用场景补充。
    //一般retrofit不好解决的场景，我们才引入它，所以我们一般将它排除。
    exclude module: 'okhttputils'

    //xlog
    exclude module: 'xlog'

    //一个远程IPC库
    exclude module: 'andlinker'
}
```

# Documentation
| 类 | 介绍 | 
|:-----:|:-----:|
|[Blankj/AndroidUtilCode][7]|一个安卓常用工具类的集合|
|[joor][1001]|非常实用简洁的JAVA反射调用类[【参考】][1002]|
|[logger][8]|android优秀的日志类，目前建议使用[xLog][1008]|
|[GsonUtil][1]|对象字符串互转类|
|[LogUtil][2]|日志打印类|
|[StringUtil][4]|一些常用的字符串操作类,对`common-io`的补充|
|[HttpUrlUtil][9]|HttpUrl的解析|
|[AndroidProcesses][10]|一个获取当前系统所有进程的库（不支持5.x以上）|
|[epic][11]|Epic是一个在虚拟机层面、以Java Method为粒度的 运行时 AOP Hook框架。|
|[EasySP][12]|EasySP是一个简单的Android SharedPreferences工具类。|
|[okhttputils][13]|okhttputils是一个安卓上方便HTTP请求的工具类，作为retrofit的一个应用场景补充。|
|[LoggerConfigeration][14]|`logback`日志模块的封装，用于打印日志到控制台，或者到文件|
|[xLog][1008]|`xLog`日志模块，建议使用这个|
|[AndLinker][1009]|`AndLinker`远程IPC模块|

# 强烈推荐的库
| 库 | 介绍 | 
|:-----:|:-----:|
|[android-common][1003]|一个轻量级的安卓工具包，包含各种加密解密，以及常用字符串文件夹处理类|
|[AndroidUtilCode][1004]|一个比较完整的安卓通用代码库[【说明】][1005]|
|[Andromeda][1006]|爱奇艺的一个跨进程交互库（不能跨APP）|
|[butterknife][1007]|ui绑定库|
|[awesome-android-ui][1010]|UI库的收集|
|[android-open-project][1011]|安卓优秀开源库的集合|

# 一些个人常用信息收集
[安卓开发规范](https://github.com/Blankj/AndroidStandardDevelop#5-%E8%B5%84%E6%BA%90%E6%96%87%E4%BB%B6%E8%A7%84%E8%8C%83)  
[Android 流行框架查速表](https://www.ctolib.com/cheatsheets-Android-ch.html)

# 参考与引用项目
[jingle1267/android-utils](https://github.com/jingle1267/android-utils)  
[solary2014/Open-Source-Android](https://github.com/solary2014/Open-Source-Android)  
[litesuits/android-common](https://github.com/litesuits/android-common)  
感谢以上作者提供的代码(如果引用代码有争议请联系本人)。

# [更新日志](UPDATE_LOG.md)


# 作者联系方式
邮箱：kigkrazy@163.com  
QQ：358778849


[1]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/GsonUtil.java
[2]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/LogUtil.java
[4]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/StringUtil.java
[5]: https://github.com/kigkrazy/andrutil/blob/master/doc/README.md
[7]: https://github.com/Blankj/AndroidUtilCode
[8]: https://github.com/orhanobut/logger
[9]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/HttpUrlUtil.java
[10]: https://github.com/jaredrummler/AndroidProcesses
[11]: https://github.com/tiann/epic
[12]: https://github.com/WhiteDG/EasySP
[13]: https://github.com/kigkrazy/okhttputils
[14]: doc/LOG-MODULE.md

[1001]: https://github.com/jOOQ/jOOR
[1002]: https://github.com/hl85/openq-blog/blob/75e5a267323e5c84188b2a3199799dab995d43de/posts/joor-source-code-analysis.md
[1003]: https://github.com/litesuits/android-common
[1004]: https://github.com/Blankj/AndroidUtilCode
[1005]: https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/README-CN.md
[1006]: https://github.com/iqiyi/Andromeda
[1007]: https://github.com/JakeWharton/butterknife
[1008]: https://github.com/elvishew/xLog
[1009]: https://github.com/codezjx/AndLinker
[1010]: https://github.com/wasabeef/awesome-android-ui
[1011]: https://github.com/Trinea/android-open-project

