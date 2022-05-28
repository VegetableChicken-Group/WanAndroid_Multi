package com.ndhzs.api.test

import android.content.Context
import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.template.IProvider
import java.io.Serializable

/**
 * 命名规范：以 I 开头表示一个接口，以 Service 结尾表示服务
 */
interface ITestService : IProvider {
  
  /**
   * 启动 TestActivity
   *
   * 其实可以按照
   * ARouter.getInstance()
   *   .build(TEST_ENTRY)
   *   .withObject(...)
   *   .navigation()
   * 来启动
   *
   * 但上面这样有缺点，就是参数不是由被启动者来决定的，所以在需要复杂参数时建议使用下面这种方式
   */
  fun startTestActivity(context: Context, data: Data)
  
  /**
   * 返回一个数据
   */
  fun getData(): Data?
  
  /**
   * 返回一个 LiveData
   *
   * 调用方可以得到这个 LiveData 来观察数据的变动
   */
  val liveData: LiveData<Data>
  
  data class Data(
    val name: String,
    val stuNum: String
  ) : Serializable
}