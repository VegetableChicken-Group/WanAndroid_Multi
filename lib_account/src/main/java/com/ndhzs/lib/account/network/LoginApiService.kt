package com.ndhzs.lib.account.network

import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.common.network.ApiGenerator
import com.ndhzs.lib.common.network.ApiStatus
import com.ndhzs.lib.common.network.ApiWrapper
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
  
  companion object {
    val INSTANCE by lazy {
      ApiGenerator.getApiService(LoginApiService::class)
    }
  }
}