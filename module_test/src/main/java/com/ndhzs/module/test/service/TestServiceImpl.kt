package com.ndhzs.module.test.service

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.g985892345.provider.annotation.SingleImplProvider
import com.ndhzs.api.test.ITestService
import com.ndhzs.module.test.page.TestActivity

/**
 * 命名规范：XXXServiceImpl，其中 Impl 是指接口的实现类
 *
 * 这个类会变成单例类
 */
@SingleImplProvider(clazz = ITestService::class)
object TestServiceImpl : ITestService {
  
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
}