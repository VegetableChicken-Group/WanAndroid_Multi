plugins {
  id("module-manager")
}

dependApiInit()

dependRxjava()

dependencies {
  implementation(Android.appcompat)
  implementation(Android.constraintlayout)
  implementation(Android.material)
}
