package project

import project.base.BaseLibraryProject

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/28 12:27
 */
object ApiProject : BaseLibraryProject() {
  override fun initProject() {
    // api 模块不主动依赖 lib_common，应尽量做到只有接口和简单逻辑
  }
}