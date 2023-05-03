package com.ndhzs.lib.account.network

import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.utils.network.ApiStatus
import com.ndhzs.lib.utils.network.ApiWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/29 22:20
 */
@Suppress("SpellCheckingInspection")
interface LoginApiService {
  
  /**
   * 注意：
   * 网络请求返回的数据类统一用 [ApiWrapper] 或者 [ApiStatus] 包裹
   *
   * 原因：
   * 一个大项目的对于网络请求有一定的约束，
   * 比如网络请求的返回格式有固定的外层 json，通常包含 code、meg 字段
   *
   * 这两个字段中包含一些异常信息，为了统一处理，所以使用 [ApiWrapper] 和 [ApiStatus] 进行包裹
   */
  
  @POST("/user/login")
  @FormUrlEncoded
  fun login(
    @Field("username") username: String,
    @Field("password") password: String
  ): Single<ApiWrapper<IAccountService.LoginBean>>
  
  @GET("/user/logout/json")
  fun logout(): Single<ApiStatus>
  
  @POST("/user/register")
  @FormUrlEncoded
  fun register(
    @Field("username") username: String,
    @Field("password") password: String,
    @Field("repassword") rePassword: String
  ): Single<ApiWrapper<IAccountService.LoginBean>>
}