# Android工具类库
囊括了一大部分Android应用开发过程当中常用的工具类。工具类来源整理自网络和自己编写。  
每个类都会在开头提供用例。

# 使用
1. 添加远程仓库
```
repositories {  
    jcenter()  
    maven { 
        url "https://raw.githubusercontent.com/kigkrazy/reizx-repo/master" 
    }  
}  
```
2. 添加引用
```
compile 'com.reizx:andrutil:1.4'
```

# 依赖的类库以及工具类:
| 类 | 介绍 | 
|:-----:|:-----:|
|[Blankj/AndroidUtilCode][7]|大量引用该类|
|[joor][1001]|非常实用简洁的JAVA反射调用类[【参考】][1002]|
|[GsonUtil][1]|对象字符串互转类|
|[LogUtil][2]|日志打印类|
|[StringUtil][4]|一些常用的字符串操作类|
|[AssetsUtil][6]|assets资源操作|

# 其他常用工具库
| 库 | 介绍 | 
|:-----:|:-----:|
|[android-common][1003]|一个轻量级的安卓工具包，包含各种加密解密，以及常用字符串文件夹处理类|
|[AndroidUtilCode][1004]|一个比较完整的安卓通用代码库[【说明】][1005]|

# 目前本库依赖以下常用库,
```
    //====================================================
    //一些常用工具类
    // https://mvnrepository.com/artifact/commons-io/commons-io
    compile group: 'commons-io', name: 'commons-io', version: '2.4'
    compile 'com.blankj:utilcode:1.10.0'
    compile 'com.google.code.gson:gson:2.8.1'

    //====================================================
    // rxjava2 && retrofit2  用于网络请求与响应式编程
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    // Because RxAndroid releases are few and far between, it is recommended you also
    // explicitly depend on RxJava's latest version for bug fixes and new features.
    compile 'io.reactivex.rxjava2:rxjava:2.1.7'
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
    compile 'com.squareup.retrofit2:converter-scalars:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.2.0'
    // https://mvnrepository.com/artifact/org.jooq/joor
    compile group: 'org.jooq', name: 'joor', version: '0.9.7'

```
只需要引用一个我们的库，上面那些常用依赖库就会被全部引用。

# 项目打包
```
gradlew uploadArchives
```
# 一些个人常用信息收集
[安卓开发规范](https://github.com/Blankj/AndroidStandardDevelop#5-%E8%B5%84%E6%BA%90%E6%96%87%E4%BB%B6%E8%A7%84%E8%8C%83)  
[Android 流行框架查速表](https://www.ctolib.com/cheatsheets-Android-ch.html)


执行上面命令会在项目根目录生成`pubrepo`文件夹，并且生成打包好的JAR包，即可以发布的到MAVEN仓库的相关文件。
# 参考与引用项目
[jingle1267/android-utils](https://github.com/jingle1267/android-utils)  
[solary2014/Open-Source-Android](https://github.com/solary2014/Open-Source-Android)  
[litesuits/android-common](https://github.com/litesuits/android-common)  
感谢以上作者提供的代码(如果引用代码有争议请联系本人)。

# 作者联系方式
邮箱：KigKrazy@163.com  
QQ：358778849

[1]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/GsonUtils.java
[2]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/LogUtils.java
[4]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/StringUtils.java
[5]: https://github.com/kigkrazy/andrutil/blob/master/doc/README.md
[6]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/AssetsUtil.java
[7]: https://github.com/Blankj/AndroidUtilCode

[1001]: https://github.com/jOOQ/jOOR
[1002]: https://github.com/hl85/openq-blog/blob/75e5a267323e5c84188b2a3199799dab995d43de/posts/joor-source-code-analysis.md
[1003]: https://github.com/litesuits/android-common
[1004]: https://github.com/Blankj/AndroidUtilCode
[1005]: https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/README-CN.md

