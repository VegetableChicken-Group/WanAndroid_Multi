package com.ndhzs.api.init

/**
 * spi 依赖注入
 *
 * 在自己的模块中参考以下写法即可实现注入：
 * ```
 * 添加依赖：
 * dependApiInit()
 *
 * 实现接口：
 * @SingleImplProvider(InitialService::class, "XXXInitialService)
 * object XXXInitialService : InitialService // 注意使用 object
 * ```
 *
 * @author ZhiQiang Tu
 * @time 2022/3/24  12:40
 * @signature 我将追寻并获取我想要的答案
 */
interface InitialService {
    // 所有进程的回调
    fun onAllProcess(manager: InitialManager) {}
    // 处于主进程的调用(可以进行与隐私策略无关的sdk的初始化,因为app启动就会回调)
    fun onMainProcess(manager: InitialManager) {}
    // 处于sdk所对应的进程的时候的回调
    fun onOtherProcess(manager: InitialManager) {}
}