package com.ndhzs.lib.common.ui

import android.content.Context
import androidx.lifecycle.*
import com.ndhzs.lib.common.BaseApp

abstract class BaseViewModel : ViewModel() {
  
  val appContext: Context
    get() = BaseApp.appContext
  
}