import com.ndhzs.build.logic.depend.*
import com.ndhzs.build.logic.depend.api.*

plugins {
    id("module-manager")
    // id("module-debug")
}

dependAndroidView()
dependAndroidKtx()
dependNetwork()
dependCoroutines()
dependPaging()
dependGlide()
dependApiMain()

dependencies {
    implementation(project(":lib_web:api_web"))
}