
##### XFrame — Android快速开发框架(欢迎star哈)

>从小的功能做起，只做必要的封装，让开发更加简单 ———— XFrame

## XFrame简介

**XFrame**你可以理解成是一个Android开发的工具集合。我在写XFrame时就明确了方向，我不会去封装http、图片加载、ioc、orm 等这些听起来非常不错的想法。
为什么呢？这些功能现在都有很强大的框架，我也做不到那么好，而且更新很快，隔段时间就有新的框架出现，我只会封装写工具，减少一些开发成本，比如Http网络请求隔离框架等。
我的本意是为了通过封装Android中一些的复杂操作而达到简化Android应用级开发的成本，用最少的代码实现更多的功能，我们只封装那些大部分应用必须用到但有总是增加我们繁琐的工作量。也许市面上单个功能模块有现成，而且很多！我们总是需要去挨着选择，但他们之间并不一定兼容，我们需要浪费在每个项目中的修改、拷贝、维护时间，而XFrame算是你还不错的选择对象！

[Banner轮播框架连接](https://github.com/youth5201314/banner)

[详细文档请前往wiki](https://github.com/youth5201314/XFrame/wiki)

## 特性

**XFrame**主要有这些功能：

- [x] `XHttp`：Http网络请求隔离框架，解决高耦合！一行代码随意切换第三方网络请求库，可自动解析json，告别切换网络库繁琐操作
- [x] `XLog`：可输出漂亮容易查看的日志，支持Json、Xml、Map、List等格式输出，可全局配置
- [x] `XLoadingView`：简单实用的页面状态统一管理 ，加载中、无网络、无数据、出错等状态的随意切换
- [x] `XLoadingDialog`：简单实现加载等待对话框
- [x] `XPermission`：简化Android动态权限管理的操作
- [x] `XRecyclerViewAdapter`：一个用于RecyclerView Adapter的开发库，包含添加Header，Footer，加载更多,加载失败，加载到底和支持多种布局
- [x] `XCache`：缓存普通的字符串、Bitmap、Drawable、Serializable的java对象、byte数据
- [x] `XStatusBar`：实现沉浸式状态栏
- [x] `XToast`：简单的吐司使用
- [x] `Utils工具类集合`：内置常用工具类
- [x] `自定义View`：内置常用自定义控件

## 使用步骤

#### Step 1.依赖XFrame
Gradle 
```groovy
dependencies{
    compile 'com.youth.xframe:xframe:1.1.0'
}
```
或者引用本地lib
```groovy
compile project(':xframe')
```


## Thanks

    感谢以下开源项目的作者，本项目中有些功能受你们项目灵感的启发，有些功能也用到你们的代码完成。
    对此如果有什么意见请与我联系！再次感谢！

- [orhanobut/logger](https://github.com/orhanobut/logger)
- [laobie/StatusBarUtil](https://github.com/laobie/StatusBarUtil)
- [GrenderG/Toasty](https://github.com/GrenderG/Toasty)
- [Blankj/AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)
- [l123456789jy/Lazy](https://github.com/l123456789jy/Lazy)
- [Jude95/EasyRecyclerView](https://github.com/Jude95/EasyRecyclerView)
- [jkyeo/Android-SplashView](https://github.com/jkyeo/Android-SplashView)
- [czy1121/loadinglayout](https://github.com/czy1121/loadinglayout)
- [yangfuhai/ASimpleCache](https://github.com/yangfuhai/ASimpleCache)


## 更新说明

#### v1.1.0
    banner 优化更新
 * 增加Http网络隔离框架
 * 修改优化了一些bug






















## License

```  
Copyright 2017 youth5201314

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```