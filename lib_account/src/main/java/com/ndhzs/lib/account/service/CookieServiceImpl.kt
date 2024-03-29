package com.ndhzs.lib.account.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.ndhzs.lib.config.route.COOKIE_SERVICE
import com.ndhzs.lib.utils.extensions.lazyUnlock
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * ...
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/8/9 15:58
 */
@Route(path = COOKIE_SERVICE)
class CookieServiceImpl : IProvider, CookieJar {

  private lateinit var mContext: Context

  private val mCookieJar by lazyUnlock {
    PersistentCookieJar(
      SetCookieCache(), SharedPrefsCookiePersistor(mContext)
    )
  }

  internal fun clearCookie() {
    mCookieJar.clear()
  }

  override fun init(context: Context) {
    mContext = context
  }

  override fun loadForRequest(url: HttpUrl): List<Cookie> {
    return mCookieJar.loadForRequest(url)
  }

  override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
    mCookieJar.saveFromResponse(url, cookies)
  }
}