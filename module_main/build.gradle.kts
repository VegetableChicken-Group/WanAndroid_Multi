import com.ndhzs.build.logic.depend.api.dependApiTest
import com.ndhzs.build.logic.depend.dependAndroidKtx
import com.ndhzs.build.logic.depend.dependAndroidView

plugins {
  id("module-manager")
}

dependAndroidView()
dependAndroidKtx()

dependApiTest()
