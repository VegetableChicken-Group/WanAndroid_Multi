# WanAndroid_Multi

某菜鸡小组的多人开发练习

## 项目简介

因为接口不好找，所以就直接用 `wanAndroid` 来练习了

### 多模块练习

项目使用了多模块，虽然有点小题大做，但为了能更好的接手掌邮，提前练习一下还是有必要

大致分为以下类型

- `build_logic`：多模块的 `gradle` 插件管理（如果有能力的可以去看一看怎么实现的）
- `lib_common`：基础模块，其他模块都会依赖的模块
- `lib_account`：只管理账号信息的模块
- `module_app`：应用的启动模块，**里面并不包含启动界面**，主要是用来配置一些东西
- `module_main`：主模块，包含应用的启动界面
- `module_test`：用来演示多模块开发的测试模块
  - `api_test`：`module_test` 模块公开的接口
- .......

对于多模块开发，我写了一篇[教程](md/多模块指南.md)，看懂并实践后就可以正式开发

如果只想看多模块构建相关代码，请查看 [`Framework`](https://github.com/VegetableChicken-Group/WanAndroid_Multi/tree/framework) 
分支，多模块相关问题将在该分支上进行维护，`master` 分支用于 `wanAndroid` 项目开发



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

```
[type]title
describtion
```

其中 `type` 有如下约定：

- fix：修护 bug
- feature：新增需求
- optimize：优化代码
- release：版本升级
- style：代码格式修改，一般是添加注释
- document：文档更新
- ......

如果觉得这些不能完整的表述你的的提交分类，可以使用 github 的表情包：[gitmoji | An emoji guide for your commit messages](https://gitmoji.dev/)，提交信息都很全，网页翻译一下

使用时建议再下载这个插件：

![image-20220719123913736](https://img-1307243988.cos.ap-chengdu.myqcloud.com/typora-after-22-7-19/image-20220719123913736.png)

使用时在 commit 窗口点击一下就可以快速添加表情包到 commit 的头部

![image-20220719124131226](https://img-1307243988.cos.ap-chengdu.myqcloud.com/typora-after-22-7-19/image-20220719124131226.png)

##### 注意事项

如果手写的话，这个 `:memo:` 后要空一格！

缺点也很明显，就是 idea 的 Git 窗口并没有支持这个表情包的显示，**对于掌邮能否使用，还需要进一步讨论！**



### pr练习
该项目中以 `master` 作为主分支，主分支有以下规定：

- 只能读，不能写，即只能 `pull`，不能 `push`	
- 可用于本地 `merge` 操作
- 主分支只能采用 `pr` 的方式来推进

具体的操作教程可以看：[`pr` 教程](md/pr教程.md)
