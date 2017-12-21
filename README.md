# Android工具类库
囊括了一大部分Android应用开发过程当中常用的工具类。工具类来源整理自网络和自己编写。  
每个类都会在开头提供用例。

# 所有的工具类简介:
| 类 | 介绍 | 
|:-----:|:-----:|
|["litesuits/android-common"][5]|直接引用该库的全部类，稍后整理|
|[GsonUtil][1]|对象字符串互转类|
|[LogUtil][2]|日志打印类|
|[StringUtil][4]|一些常用的字符串操作类|
|[AssetsUtil][6]|assets资源操作|




# 其他常用工具库
| 库 | 介绍 | 
|:-----:|:-----:|
|[joor][1001]|非常实用简洁的JAVA反射调用类[【参考】][1002]|
|[android-common][1003]|一个轻量级的安卓工具包，包含各种加密解密，以及常用字符串文件夹处理类|
|[AndroidUtilCode][1004]|一个比较完整的安卓通用代码库[【说明】][1005]|

# 使用
## 源码编译
* 进入项目文件夹
```
git subtree add --prefix=andrutil https://github.com/kigkrazy/andrutil.git master
```

* 修改`settings.gradle`
```
//settings.gradle
//增加':andrutil'模块
include ':app', ':andrutil'

//为xdao指定路径
project(':andrutil').projectDir = new File(settingsDir, 'andrutil/util')
project(':andrutil').buildFileName = 'build.gradle'
```

* 在项目中更新`andrutil`代码
```
git subtree pull --prefix=andrutil https://github.com/kigkrazy/andrutil.git master
```

* 使用`subtree`提交子项目代码
[参考文章](https://segmentfault.com/a/1190000003969060)
## maven仓库引用
添加远程仓库
```
repositories {  
    jcenter()  
    maven { 
        url "https://github.com/kigkrazy/reizx-repo/blob/master" 
    }  
}  
```
添加引用
```
compile 'com.reizx:andrutil:1.0'
```
**由于目前还处于开发阶段，还未发布到jcenter仓库**

# 项目打包
```
gradlew uploadArchives
```
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
[5]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/AssetsUtil.java

[1001]: https://github.com/jOOQ/jOOR
[1002]: https://github.com/hl85/openq-blog/blob/75e5a267323e5c84188b2a3199799dab995d43de/posts/joor-source-code-analysis.md
[1003]: https://github.com/litesuits/android-common
[1004]: https://github.com/Blankj/AndroidUtilCode
[1005]: https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/README-CN.md

