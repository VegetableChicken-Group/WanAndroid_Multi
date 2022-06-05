package lib

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *
 * @author :WhiteNight123(Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @date 2022/5/31
 */
@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object Compose {
    // 基础库
    const val compose_version = "1.2.0-beta02"

    // 官方控件控件库
    const val activity = "androidx.activity:activity-compose:1.4.0"
    const val material = "androidx.compose.material:material:$compose_version"
    const val animation = "androidx.compose.animation:animation:$compose_version"
    const val `ui-tool` = "androidx.compose.ui:ui-tooling:$compose_version"
    const val `ui-test` = "androidx.compose.ui:ui-test-junit4:$compose_version"
    const val `ui-tool-debug` = "androidx.compose.ui:ui-tooling:$compose_version"
    const val `ui-test-debug` = "androidx.compose.ui:ui-test-manifest:$compose_version"


}

fun Project.dependCompose() {
    dependencies {
        "implementation"(Compose.activity)
        "implementation"(Compose.material)
        "implementation"(Compose.animation)
        "implementation"(Compose.`ui-tool`)
    }
}