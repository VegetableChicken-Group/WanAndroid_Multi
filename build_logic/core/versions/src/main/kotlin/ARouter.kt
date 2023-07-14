import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * todo 现在已经实现 KtProvider 代替 https://github.com/985892345/KtProvider
 *
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/27 15:45
 */
@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object ARouter {
  // https://github.com/alibaba/ARouter
  // 适配 AGP8: https://github.com/jadepeakpoet/ARouter
//  const val arouter_version = "1.5.2"
  const val arouter_version_jadepeakpoet = "1.0.3"
  
//  const val `arouter-api` = "com.alibaba:arouter-api:$arouter_version"
//  const val `arouter-compiler` = "com.alibaba:arouter-compiler:$arouter_version"
  const val `arouter-api` = "com.github.jadepeakpoet.ARouter:arouter-api:$arouter_version_jadepeakpoet"
//  const val `arouter-compiler` = "com.github.jadepeakpoet.ARouter:arouter-compiler:$arouter_version_jadepeakpoet"
  
  // 第三方的 ksp 版本: https://github.com/JailedBird/ArouterKspCompiler
  const val `arouter-ksp` = "com.github.JailedBird:ArouterKspCompiler:1.8.20-1.0.2"
}

/**
 * 所有使用 build_logic 插件的模块都默认依赖了 ARouter
 */
fun Project.dependARouter() {
  apply(plugin = "com.google.devtools.ksp")
  // ksp 设置
  extensions.configure<KspExtension> {
    arg("AROUTER_MODULE_NAME", project.name)
  }
  dependencies {
    "implementation"(ARouter.`arouter-api`)
//    "kapt"(ARouter.`arouter-compiler`)
    "ksp"(ARouter.`arouter-ksp`)
  }
}