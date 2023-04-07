// [account, main, test, crash]
// 自动生成的代码，请不要修改 !!!
import org.gradle.api.Project

fun Project.dependApiAccount() {
  ApiDepend.account.dependApiOnly(this)
}

fun Project.dependApiMain() {
  ApiDepend.main.dependApiOnly(this)
}

fun Project.dependApiTest() {
  ApiDepend.test.dependApiOnly(this)
}

fun Project.dependApiCrash() {
  ApiDepend.crash.dependApiOnly(this)
}