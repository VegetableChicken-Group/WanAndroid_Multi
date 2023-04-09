@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import utils.ApiDependUtils

/**
 * 为什么要把 api 模块单独写出来？
 *
 * 因为单模块调试时 ARouter 如果不把实现模块一起拿来编译，就会报空指针，
 * 但谁是 api 模块的实现模块是不能被定义的（虽然目前只有父模块是实现模块），
 * 所以为了单模块调试，需要统一 api 模块的依赖写法
 *
 * 写了后会由一个 gradle 脚本自动生成对应 dependApi*() 方法
 *
 * @author 985892345
 * @date 2022/11/9 17:26
 */
object ApiDepend {
  /*
  * 这里写 api 模块以及该 api 模块的实现模块
  * 写法如下：
  * val test = ":module_test:api_test" by parent and ":module_xxx"
  *                                    ↑    ↑     ↑        ↑
  * by:     通过 by 来连接实现模块的 path
  * parent: 如果父模块是实现模块，则使用该方式可直接添加
  * and:    用于连接多个实现模块，比如后面写的 module_xxx，就是 api_test 的另一个实现模块
  *
  * 写了后会由一个 gradle 脚本自动生成对应 dependLib*() 方法
  * */
  
  val account = ":lib_account:api_account" by parent
  val main = ":module_main:api_main" by parent
  val test = ":module_test:api_test" by parent
  val crash = ":lib_crash:api_crash" by parent
  
  private infix fun String.by(implPath: String): ApiDependUtils.IApiDependUtils = by { implPath }
  private infix fun String.by(implPath: String.() -> String): ApiDependUtils.IApiDependUtils {
    return ApiDependUtils(this)
      .by(implPath.invoke(this))
  }
  
  private fun String.byNoImpl(): ApiDependUtils.IApiDependUtils = ApiDependUtils(this).byNoImpl()
  
  private val parent: String.() -> String
    get() = { substringBeforeLast(":") }
}

/**
 * api_init 模块没有实现模块，所以单独写
 */
fun Project.dependApiInit() {
  dependencies {
    "implementation"(project(":api_init"))
  }
  dependAutoService()
}