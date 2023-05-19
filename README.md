# <img src="https://raw.githubusercontent.com/Wenlong-Guo/open-assets/main/img/blog/pluginIcon.svg" style="width: 26px"> Dimens-Generating

---

[![Plugin Homepage][badge:plugin-homepage]][plugin-homepage]
[![License][license-img]][license]
[![Version][version-img]][plugin]
[![Downloads][badge:downloads]][plugin-homepage]

:ballot_box_with_check: A plugin that supports multiple screens by generating dimens.xml for any width screen size using minimum width qualifier.

[![Getting Started][badge:get-started-en]][get-started-en]
[![开始使用][badge:get-started-zh]][get-started-zh]

[//]: # ([![はじめに][badge:get-started-jp]][get-started-ja])

[//]: # ([![시작하기][badge:get-started-ko]][get-started-ko])

- [Features](#Features)
- [Usage](#Usage)
- [Tips](#Tips)
- [Change Notes](#Change-Notes)
    - [V3.0.0](#V300)
    - [V2.0.5](#V205)
    - [V2.0.4](#V204)
    - [V2.0.3](#V203)
    - [V2.0.0](#V200)
    - [V1.2.0](#V120)
    - [V1.1.0](#V110)
    - [V1.0.1](#V101)
    - [V1.0.0](#V100)
- [ScreenShot](#ScreenShot)
- [License](#License)
- [About My Github](#About-My-Github)
- [About Me](#About-Me)

## Features

* Generate a dimens.xml file of the specified size (can be customized) based on the selected dimens.xml file.
* When the file to be generated already exists, you can control whether to override.
* Can generate multiple files of the specified size at the same time.
* Transformation layout.xml or layout folder DP DIP SP in the resource file.

## Usage

* Choose origin dimens.xml -> right click -> Generate Dimens -> Edit options -> Click "Generate/生成" button.
* Choose layout.xml or layout folder -> right click -> Converter Dimens -> Edit options -> Click "Converter/转换" button.

## Tips

* Welcome to improve your opinion.
* If you feel good,please star,thank you very much.

## Change Notes

### V3.0.0
1. refactor : Building a project using `Kotlin` through `Gradle`.

### V2.0.5
1. fix android studio compatibility

### V2.0.4
1. fix : issue #18

### V2.0.3
1. fix : some V2.0.0 Bugs

### V2.0.0
1. fix : issue #6
2. feature : Add `Logo`
3. feature : Transformation `layout.xml` or `layout folder` `DP` `DIP` `SP` in the resource file</li>

### V1.2.0
1. Redraw the UI
2. Add save configuration function
3. Support `English` and `Chinese`
4. Fix bug where resource attribute cannot be converted starting with "dip"</li>

### V1.1.0
1. feature : Custom decimal bit
2. feature : Generate multiple default parameter
3. feature : Custom qualifier name

### V1.0.1
1. fix : Solve the problem that the specified size of the generated file is invalid

### V1.0.0
1. First Version generate `dimens.xml`


## ScreenShot

![ScreenShot](https://plugins.jetbrains.com/files/11290/screenshot_19610.png)

## To Do List

* Support Japanese and Korean languages
* Batch generate dp and sp with specified naming conventions in dimens.xml
* Add folder naming convention for generating dimens.xml
* Enhance conversion function and provide conversion utilities for px, dp, dip, and sp referenced in the code
* View current phone screen information via adb.

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
