package com.ndhzs.module.test.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ndhzs.lib.base.ui.BaseViewModel

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/7/25 18:41
 */
class TestViewModel : BaseViewModel() {
  private val _data = MutableLiveData<Data>()
  val data: LiveData<Data>
    get() = _data
}

class Data