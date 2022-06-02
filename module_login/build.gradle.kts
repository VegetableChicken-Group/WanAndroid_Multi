import com.ndhzs.build.logic.depend.api.dependApiAccount
import com.ndhzs.build.logic.depend.*

plugins {
  id("module-manager")
//  id("module-debug")
}

dependAndroidView()
dependAndroidKtx()
dependLifecycleKtx()

dependNetwork()
dependRxjava()
dependCoroutines()
dependCoroutinesRx3()

dependApiAccount()
