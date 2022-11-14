# pr教程

如果你是本项目的成员，不用 `fork` 项目，直接 `clone` 到本地后新建分支即可开发，新分支提交到项目中即可；如果不是，请 `fork` 该项目，并新建分支（`master` 分支作为项目主分支，只允许读，不允许写，只能通过 `pr` 来推进）

在确保写好你负责的需求后，可以通过 `github` 来提交 `pr` 合并到 `master` 分支，但 `pr` 前请进行以下操作：

- 确保已经写完需求
- 与 `master` 分支进行一次本地 `merge` 合并
- 本地打出 `release` 的安装包并测试（只有 `debug` 还不行，因为 `release` 下可能出现混淆等问题）

## `pr` 操作

- 打开 `github` 项目主页，如果你已经 `push` 分支，则会有如下提示，点击绿色的 `Compare & pull request` 即可

  > ![image-20220615155144926](https://img-1307243988.cos.ap-chengdu.myqcloud.com/typora/image-20220615155144926.png)

- 确保提交的分支是否正确

  > ![image-20220615155242074](https://img-1307243988.cos.ap-chengdu.myqcloud.com/typora/image-20220615155242074.png)
  >
  > 如果没有绿色的 `Able to merge`，说明没有走之前的步骤：与 `master` 分支进行一次本地 `merge` 合并

- 编写信息，主要写你的分支干了什么

- 选择 `Reviewers` 代码审查人，点击齿轮可以看到所有人

  > ![image-20220615154409320](https://img-1307243988.cos.ap-chengdu.myqcloud.com/typora/image-20220615154409320.png)

- 等待 `CI` 打包结束后，再进行合并操作

  > ![image-20220615155936642](https://img-1307243988.cos.ap-chengdu.myqcloud.com/typora/image-20220615155936642.png)
  >
  > 合并操作有三种模式：
  >
  > - `Create a merge commit`：常用模式，它会自动生成一次合并的记录
  >
  > - `Squash and merge`：不常用（大项目中常用），将你的所有 `commit` 合并为一次 `commit` 后再与主分支合并，
      >
      >   > 注意：使用该提交模式后建议删除你的分支，如果下次 `pr` 仍使用之前的分支，会导致合并后的记录看起来很怪
  >
  > - `Rebase and merge`：有时会用，如果你的提交与主分支是一条线的情况（即是在主分支最新 `commit` 后的改动，没有出现分叉），使用该提交不会产生合并记录，一般适用小 `bug` 修护的场景，
      >
      >   >  注意：与 `Squash and merge` 一样，合并后需要删除源分支，不然下次合并很容易出问题（才写完这个 `pr` 流程去 `pr` 合并进 `master` 就出问题了 :( ）
  >
  > 总结一下：无脑选择 `Create a merge commit` 即可



由于 `framework` 分支与 `master` 分支相差甚远，所以只能我自己手动合并进 `master` 再上传 github，所以没有使用 pr，有点违反约定，但为了继续维护 `framework` 分支，所以这是没办法的办法（