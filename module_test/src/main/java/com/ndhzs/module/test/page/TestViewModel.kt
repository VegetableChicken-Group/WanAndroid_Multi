package com.ndhzs.module.test.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ndhzs.lib.base.ui.BaseViewModel
import com.ndhzs.lib.utils.extensions.defaultHandler
import com.ndhzs.lib.utils.extensions.postDelay
import kotlin.random.Random

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/7/25 18:41
 */
class TestViewModel : BaseViewModel() {
  
  private val _data = MutableLiveData<Int>()
  val data: LiveData<Int>
    get() = _data
  
  fun refresh() {
    // 模拟网络请求
    defaultHandler.postDelay(100) {
      // 发送数据给 _data
      _data.value = Random.nextInt()
    }
  }
}
