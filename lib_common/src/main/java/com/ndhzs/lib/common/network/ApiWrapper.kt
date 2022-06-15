package com.ndhzs.lib.common.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.jvm.Throws

/**
 * [ApiWrapper] 里面封装了 [data]、[errorCode]、[errorMsg] 字段，是为了统一网络请求数据的最外层结构，
 * 如果你遇到了 json 报错，可能是你数据类写错了，只需要提供 [data] 对应的类即可
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 23:06
 */
open class ApiWrapper<T> (
  @SerializedName("data")
  val data: T,
) : Serializable, ApiStatus()

open class ApiStatus(
  @SerializedName("errorCode")
  val errorCode: Int = 0,
  @SerializedName("errorMsg")
  val errorMsg: String = ""
) : Serializable {
  
  fun isSuccess(): Boolean {
    return errorCode == 0
  }
  
  @Throws(ApiException::class)
  fun throwApiExceptionIfFail() {
    if (!isSuccess()) throw ApiException(errorCode, errorMsg)
  }
}
