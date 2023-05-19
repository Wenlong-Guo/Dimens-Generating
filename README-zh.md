# <img src="https://raw.githubusercontent.com/Wenlong-Guo/open-assets/main/img/blog/pluginIcon.svg" style="width: 26px"> Dimens-Generating

---

[![Plugin Homepage][badge:plugin-homepage]][plugin-homepage]
[![License][license-img]][license]
[![Version][version-img]][plugin]
[![Downloads][badge:downloads]][plugin-homepage]

:ballot_box_with_check: 安卓最小宽度限定符插件.(生成多套dimens文件以及转换没有使用dimens的layout文件或者文件夹)

[![Getting Started][badge:get-started-en]][get-started-en]
[![开始使用][badge:get-started-zh]][get-started-zh]

[//]: # ([![はじめに][badge:get-started-jp]][get-started-ja])

[//]: # ([![시작하기][badge:get-started-ko]][get-started-ko])

- [功能](#功能)
- [用法](#用法)
- [小贴士](#小贴士)
- [变更说明](#变更说明)
  - [V3.0.0](#V300)
  - [V3.0.0](#V300)
  - [V3.0.0](#V300)
  - [V3.0.0](#V300)
  - [V3.0.0](#V300)
- [ScreenShot](#ScreenShot)
- [License](#License)
- [About My Github](#About-My-Github)
- [About Me](#About-Me)

## 功能

* 根据所选的dimens.xml文件生成指定尺寸（可自定义）的dimens.xml文件。
* 当要生成的文件已经存在时，可以控制是否覆盖。
* 可以同时生成多个指定大小的文件。
* 在资源文件中，将layout.xml文件或layout文件夹中的DP、DIP或SP进行转换。

## 用法

* 选择dimens.xml -> 右键 -> Generate Dimens -> 编辑选项 -> 点击 "Generate/生成" 按钮。
* 选择layout.xml或者layout文件夹 -> 右键 -> Converter Dimens -> 编辑选项 -> 点击 "Converter/转换" 按钮。

## 小贴士

* 欢迎提出您的意见和建议。
* 如果您感觉该产品不错，请为我们点赞，非常感谢。

## 变更说明

### V3.0.0
1. 重构：使用 `Kotlin` 和 `Gradle` 构建项目。

### V2.0.5
1. 修复：与 Android Studio 兼容性问题

### V2.0.4
1. 修复 修复 issue #18

### V2.0.3
1. 修复：修复了一些 V2.0.0 中的错误。

### V2.0.0
1. 修复：修复 issue #6
2. 功能：添加 `Logo`
3. 功能：在资源文件中，将`layout.xml`或`layout folder`中的`DP` `DIP` `SP`进行转换。

### V1.2.0
1. 重新设计了用户界面
2. 添加了保存配置函数
3. 支持`英语`和`中文`
4. 修复了资源属性以“dip”开头时无法转换的错误。

### V1.1.0
1. 功能：自定义小数点位数
2. 功能：生成多个默认参数
3. 功能：自定义限定符名称

### V1.0.1
1. 修正：解决了生成文件的指定大小无效的问题。

### V1.0.0
1. 初始版本：生成 `dimens.xml`。

## ScreenShot

![ScreenShot](https://plugins.jetbrains.com/files/11290/screenshot_19610.png)

## To Do List

* 支持日语和韩语
* 在dimens.xml批量生成指定命名规则的dp和sp
* 增加生成dimens.xml的文件夹命名规则
* 增强转换功能 附赠代码中引用的px dp dip sp的转换工具类
* 通过adb查看当前手机的屏幕信息

License
-------

    Copyright 2023 Wenlong Guo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## About Me
<div align="left">
    <a href="https://juejin.cn/user/3931509310370701/posts"><img src="https://img.shields.io/badge/Website-掘金-blue" /></a>&emsp;
<!--     <a href="https://twitter.com/sun0225SUN/"><img src="https://img.shields.io/badge/Twitter-推特-blue" /></a>&emsp; -->
<!--     <a href="https://www.youtube.com/@sun0225SUN"><img src="https://img.shields.io/badge/YouTube-油管-c32136" /></a>&emsp; -->
<!--     <a href="https://box.sunguoqi.com/weixin_mp"><img src="https://img.shields.io/badge/WeChat-微信-07c160" /></a>&emsp; -->
    <a href="https://space.bilibili.com/321426902"><img src="https://img.shields.io/badge/Bilibili-B站-ff69b4" /></a>&emsp;
<!--     <a href="https://blog.csdn.net/weixin_50915462/"><img src="https://img.shields.io/badge/CSDN-论坛-c32136" /></a>&emsp; -->
<!--     <a href="https://www.zhihu.com/people/sunguoqi/"><img src="https://img.shields.io/badge/Zhihu-知乎-blue" /></a>&emsp; -->
    <!-- visitor statistics logo 访客数统计徽标 -->
    <img src="https://komarev.com/ghpvc/?username=Wenlong-Guo&label=Views&color=0e75b6&style=flat" alt="访问量统计" />
    <!--  <img src="https://visitor-badge.glitch.me/badge?page_id=sun0225SUN" alt="访客统计" /> -->
</div>

- 📍 &nbsp;&nbsp;北京(Beijing)
- 👨‍🎓 &nbsp;NKY.
- 👩‍💻 8 years of work.
- 🏢 待业 (求内推)
- ☎️ 17600133786
- wx : xiaoguo9745
- 📧  [guowenlong20000@sina.com](mailto:guowenlong20000@sina.com)
- ℹ️ 24k纯90后 没几年就35毕业了,毕业前开始总结安卓的经验,也算是给自己的这些年一个交代

## About My Github

<br/> 
<div align="left">
<img height='170' src="https://github-readme-stats.vercel.app/api/top-langs/?username=Wenlong-Guo&layout=compact&langs_count=8&theme=cobalt" align="center" />
<img height='170' src="https://github-readme-stats.vercel.app/api?username=Wenlong-Guo&show_icons=true&theme=cobalt" align="center" />
</div>
<br></br>
<img  alt="GIF" src="https://raw.githubusercontent.com/Wenlong-Guo/open-assets/main/img/blog/gif3.gif"/>

[license-img]: https://img.shields.io/badge/License-MIT-blue.svg
[license]: https://github.com/Wenlong-Guo/Dimens-Generating/blob/master/LICENSE
[version-img]: https://img.shields.io/badge/Jetbrains%20Plugins-V2.0.5-blue.svg
[plugin]: https://plugins.jetbrains.com/plugin/11290

[badge:plugin-homepage]: https://img.shields.io/badge/plugin--homepage-Dimens--Generating-blue
[badge:downloads]: https://img.shields.io/jetbrains/plugin/d/11290.svg?style=flat-square&colorB=blue

[plugin-homepage]: https://plugins.jetbrains.com/plugin/11290-dimens-generating
[dimens-generating-plugin]: https://plugins.jetbrains.com/plugin/11290-dimens-generating
[plugin-homepage]: https://plugins.jetbrains.com/plugin/8579-translation

[badge:get-started-en]: https://img.shields.io/badge/Get%20Started-English-4CAF50?style=flat-square
[badge:get-started-zh]: https://img.shields.io/badge/%E5%BC%80%E5%A7%8B%E4%BD%BF%E7%94%A8-%E4%B8%AD%E6%96%87-2196F3?style=flat-square
[badge:get-started-jp]: https://img.shields.io/badge/%E3%81%AF%E3%81%98%E3%82%81%E3%81%AB-%E6%97%A5%E6%9C%AC%E8%AA%9E-009688?style=flat-square
[badge:get-started-ko]: https://img.shields.io/badge/%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0-%ED%95%9C%EA%B5%AD%EC%96%B4-7CB342?style=flat-square

[get-started-en]: https://github.com/Wenlong-Guo/Dimens-Generating/blob/master/README.md
[get-started-zh]: https://github.com/Wenlong-Guo/Dimens-Generating/blob/master/README-zh.md
[get-started-ja]: https://github.com/Wenlong-Guo/Dimens-Generating/blob/master/README-ja.md
[get-started-ko]: https://github.com/Wenlong-Guo/Dimens-Generating/blob/master/README-ko.md
