package com.ndhzs.module.test.service

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.api.test.ITestService
import com.ndhzs.lib.common.config.TEST_SERVICE
import com.ndhzs.module.test.page.TestActivity

/**
 * 命名规范：XXXServiceImpl，其中 Impl 是指接口的实现类
 *
 * 这个类会变成单例类
 */
@Route(path = TEST_SERVICE)
class TestServiceImpl : ITestService {
  
  private val mDataLive = MutableLiveData<ITestService.Data>()

  override fun startTestActivity(context: Context, data: ITestService.Data) {
    mDataLive.postValue(data)
    TestActivity.start(context, data)
  }

  override fun getData(): ITestService.Data? {
    return mDataLive.value
  }

  override val liveData: LiveData<ITestService.Data>
    get() = mDataLive

  override fun init(context: Context) {
  }
}