# Android工具类库
囊括了一大部分Android应用开发过程当中常用的工具类。工具类来源整理自网络和自己编写。  
每个类都会在开头提供用例。

# 所有的工具类简介:
| 类 | 介绍 | 
|:-----:|:-----:|
|["litesuits/android-common"][5]|直接引用该库的全部类，稍后整理|
|[GsonUtils][1]|对象字符串互转类|
|[LogUtils][2]|日志打印类|
|[ShellUtils][3]|安卓中Shell执行类|
|[StringUtils][4]|一些常用的字符串操作类|




# 其他常用工具库
| 库 | 介绍 | 
|:-----:|:-----:|
|[joor][1001]|非常实用简洁的JAVA反射调用类[【参考】][1002]|
|[android-common][1003]|一个轻量级的安卓工具包，包含各种加密解密，以及常用字符串文件夹处理类|

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
* 使用`subtree`提交子项目代码
[参考文章](https://segmentfault.com/a/1190000003969060)

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
[3]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/ShellUtils.java
[4]: https://github.com/kigkrazy/andrutil/blob/master/util/src/main/java/com/reizx/andrutil/StringUtils.java

[1001]: https://github.com/jOOQ/jOOR
[1002]: https://github.com/hl85/openq-blog/blob/75e5a267323e5c84188b2a3199799dab995d43de/posts/joor-source-code-analysis.md
[1003]: https://github.com/litesuits/android-common

