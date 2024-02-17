package com.ndhzs.api.login

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/7 21:03
 */
interface ILoginService : IProvider {
  
  fun start(context: Context)
}