import com.ndhzs.build.logic.depend.*

plugins {
//    id("module-manager")
    id("module-debug")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependAndroidView()
dependAndroidKtx()
dependNetwork()
dependPaging()
dependRxjava()
dependRoom()
dependRoomPaging()


dependencies {
    implementation("io.coil-kt:coil:2.1.0")
    implementation("io.coil-kt:coil-gif:2.1.0")
}
