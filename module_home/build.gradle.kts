import com.ndhzs.convention.depend.*
import com.ndhzs.convention.depend.api.*

plugins {
    id("module-manager")
    // id("module-debug")
}

dependNetwork()
dependCoroutines()
dependPaging()
dependGlide()

dependApiMain()
dependApiWeb()
