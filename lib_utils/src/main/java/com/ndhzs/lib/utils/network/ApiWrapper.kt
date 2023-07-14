package com.ndhzs.lib.utils.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Collections
import kotlin.jvm.Throws

/**
 * [ApiWrapper] 里面封装了 [data]、[errorCode]、[errorCode] 字段，是为了统一网络请求数据的最外层结构
 *
 * ## 注意
 * - 如果你遇到了 json 报错，可能是你数据类写错了，只需要提供 [data] 对应的类即可
 * - 如果你的网络请求数据类有其他变量，请使用 [IApiWrapper] 这个接口
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 23:06
 */
data class ApiWrapper<T>(
  @SerializedName("data")
  override val data: T,
  @SerializedName("errorCode")
  override val errorCode: Int,
  @SerializedName("errorMsg")
  override val errorMsg: String
) : IApiWrapper<T>

/**
 * 没有 data 字段的接口数据包裹类
 *
 * 该类符合后端的接口规范，最外层字段值包含 [errorCode] 和 [errorCode]
 *
 * 禁止私自添加其他字段
 * - 如果需要添加且不是老接口，那说明是后端没有遵守规范，让后端自己改接口
 * - 如果是老接口，请自己使用 map 操作符判断
 */
data class ApiStatus(
  @SerializedName("errorCode")
  override val errorCode: Int,
  @SerializedName("errorMsg")
  override val errorMsg: String
) : IApiStatus

interface IApiWrapper<T> : IApiStatus {
  val data: T
}

interface IApiStatus : Serializable {
  val errorCode: Int
  val errorMsg: String
  
  /**
   * 数据的状态码
   *
   * 注意区分：数据 Http 状态码 与 数据状态码
   *
   * 对于 [errorCode] 不为 0 时，建议采用下面这种写法来处理
   * ```
   *
   * ```
   */
  fun isSuccess(): Boolean {
    Collections.unmodifiableMap()
    HashMap
    return errorCode == 0 // 请不要私自加其他的成功状态！！！
  }
  
  @Throws(ApiException::class)
  fun throwApiExceptionIfFail() {
    if (!isSuccess()) throw ApiException(errorCode, errorMsg)
  }
}

