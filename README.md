# WanAndroid_Multi

某菜鸡小组的多人开发练习

## 项目简介

因为接口不好找，所以就直接用 `wanAndroid` 来练习了

### 多模块练习

项目使用了多模块，大致分为以下类型

- `build_logic`：多模块的 `gradle` 插件管理（如果有能力的可以去看一看怎么实现的）
- `lib_base`：基础模块，包含 UI 基础层
- `lib_utils`：共用的工具模块
- `lib_config`：共用的配置模块
- `lib_account`：只管理账号信息的模块
- `module_app`：应用的启动模块，**里面并不包含启动界面**，主要是用来配置一些东西
- `module_main`：主模块，包含应用的启动界面
- `module_test`：用来演示多模块开发的测试模块
  - `api_test`：`module_test` 模块公开的接口
- .......

对于多模块开发，我写了一篇[教程](doce/多模块指南.md)，看懂并实践后就可以正式开发

如果只想看多模块构建相关代码，请查看 [`Framework`](https://github.com/VegetableChicken-Group/WanAndroid_Multi/tree/framework) 
分支，多模块相关问题将在该分支上进行维护，`master` 分支用于 `wanAndroid` 项目开发(现已废弃，原因请看 [后文](#公告))

如果你没有多模块项目的经验，或者不知道该如何入手，请查看下面内容：
- `lib_account` 模块：里面包含了网络请求的示例写法
- `api_account`、`api_test` 模块：里面包含了 `api` 模块的示例写法
- `lib_login` 模块：里面有一个示例的登录界面
- `lib_base` 模块：里面包含 `BaseActivity`、`BaseFragment`、`BaseViewModel`，需要你继承他们进行开发
- `lib_utils` 模块：里面包含很多扩展以及网络请求的封装
  - 比如统一的 VP2 的 Adapter：`FragmentAdapter`
  - `Flow`、`Rxjava`、`Glide`、`Toast` 扩展等
  - 强烈建议每个类都看一遍！！！
- `lib_config` 模块：包含常用的字体颜色，要求 Text 必须使用里面的颜色，请查看 color.xml 文件


### 分支开发练习

多人开发中使用多分支尤为重要

这里给出以前我写的教程：https://github.com/985892345/Git-Study

教程有些小瑕疵，但问题不大（懒得改了）

分支命名规则：XXX/xxx

- 前三个 XXX 需要表明是谁写的
- 后三个 xxx 需要表明写的什么
- 当然，第二个 xxx 还可以进行细分

举例：

- GuoXR/module_course2
- GuoXR/feature/module_course2
- GuoXR/bugfix/module_course2



### commit 提交规范

现目前的 commit 规范只允许提交表情包：[gitmoji | An emoji guide for your commit messages](https://gitmoji.dev/) 提交信息都很全，网页翻译一下
```
:type: title
               ← 这里要空一行
description
```

其中 `type` 填写对应表情包，常用的如下：

- bug：修护 bug
- sparkles：新增需求
- recycle：重构代码
- art：代码格式修改，一般是添加注释
- ......(支持 https://gitmoji.dev/ 所有表情包)

使用时再建议下载这个插件：

![image-20220719123913736](https://img-1307243988.cos.ap-chengdu.myqcloud.com/typora-after-22-7-19/image-20220719123913736.png)

在 commit 窗口点击一下就可以快速添加表情包到 commit 的头部

![image-20220719124131226](https://img-1307243988.cos.ap-chengdu.myqcloud.com/typora-after-22-7-19/image-20220719124131226.png)

##### 注意事项

- 如果手写的话，这个 `:memo:` 后要空一格！
- description 前要空一行！



### pr练习
该项目中以 `master` 作为主分支，主分支有以下规定：

- 只能读，不能写，即只能 `pull`，不能 `push`	
- 可用于本地 `merge` 操作
- 主分支只能采用 `pr` 的方式来推进

具体的操作教程可以看：[`pr` 教程](doce/pr教程.md)

### 公告
由于经过了一次多模块的大规模改动，导致 master 分支不能直接移植，所以现决定废除 master 分支  
但以后 framework 仍将继续维护

如果你想直接拿来用于快速构建其他项目，请 fork framework 分支，并全局替换 wanAndroid 字符，
其中 framework 分支业务代码大部分在 login 模块，其他模块不涉及太多的业务代码