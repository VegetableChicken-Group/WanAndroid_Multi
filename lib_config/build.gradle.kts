plugins {
  id("module-manager")
}

dependApiInit()

dependencies {
  implementation(Android.appcompat)
  implementation(Android.constraintlayout)
  implementation(Android.material)
}
