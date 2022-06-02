package com.ndhzs.build.logic.project

import com.ndhzs.build.logic.depend.*
import com.ndhzs.build.logic.project.base.BaseLibraryProject

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:22
 */
object LibCommonProject : BaseLibraryProject() {
  override fun initProject() {
    // lib_common 默认情况下是导入所有依赖
    dependAndroidView()
    dependAndroidKtx()
    dependCoroutines()
    dependCoroutinesRx3()
    dependEventBus()
    dependGlide()
    dependLifecycleKtx()
    dependLottie()
    dependNetwork()
    dependNetworkInternal()
    dependPaging()
    dependRoom()
    dependRoomRxjava()
    dependRoomPaging()
    dependRxPermissions()
  }
}