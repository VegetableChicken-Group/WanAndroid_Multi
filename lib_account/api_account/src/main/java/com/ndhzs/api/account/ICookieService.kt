package com.ndhzs.api.account

import com.alibaba.android.arouter.facade.template.IProvider
import okhttp3.CookieJar

/**
 * ...
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/8/9 15:58
 */
interface ICookieService : IProvider, CookieJar {
  fun clearCookie()
}