package com.ndhzs.lib.common.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.jvm.Throws

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 23:06
 */
open class ApiWrapper<T> (
  @SerializedName("data")
  val data: T,
  @SerializedName("errorCode")
  override val errorCode: Int,
  @SerializedName("errorMsg")
  override val errorMsg: String
) : Serializable, ApiStatue(errorCode, errorMsg)

open class ApiStatue(
  @SerializedName("errorCode")
  open val errorCode: Int,
  @SerializedName("errorMsg")
  open val errorMsg: String
) : Serializable {
  
  fun isSuccess(): Boolean {
    return errorCode == 0
  }
  
  @Throws(ApiException::class)
  fun throwApiExceptionIfFail() {
    if (!isSuccess()) throw ApiException(errorCode, errorMsg)
  }
}
