package com.ndhzs.lib.account.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.utils.internal.IUtilsAccountService
import com.ndhzs.lib.utils.internal.UTILS_ACCOUNT_SERVICE
import com.ndhzs.lib.utils.service.impl

/**
 * .
 *
 * @author 985892345
 * 2023/4/9 21:08
 */
@Route(path = UTILS_ACCOUNT_SERVICE)
class UtilsAccountServiceImpl : IUtilsAccountService {
  
  private val mAccountService = IAccountService::class.impl
  
  override fun getPublicName(): String? {
    return mAccountService.getUserInfo()?.publicName
  }
  
  override fun getId(): Int? {
    return mAccountService.getUserInfo()?.id
  }
  
  override fun init(context: Context?) {
  }
}