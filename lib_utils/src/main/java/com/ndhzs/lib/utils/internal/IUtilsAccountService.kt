package com.ndhzs.lib.utils.internal

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * 解决 api_account 因为依赖了 lib_utils 后导致的循环依赖问题
 *
 * @author 985892345
 * 2023/4/9 21:03
 */
interface IUtilsAccountService : IProvider {
  fun getPublicName(): String?
  fun getId(): Int?
}

const val UTILS_ACCOUNT_SERVICE = "/utils/account/service"