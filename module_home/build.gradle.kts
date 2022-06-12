import com.ndhzs.build.logic.depend.*
import com.ndhzs.build.logic.depend.api.*

plugins {
    id("module-manager")
    // id("module-debug")
}

dependAndroidView()
dependAndroidKtx()
dependLifecycleKtx()

dependNetwork()
dependCoroutines()
dependPaging()
dependGlide()

dependApiMain()
dependApiWeb()
