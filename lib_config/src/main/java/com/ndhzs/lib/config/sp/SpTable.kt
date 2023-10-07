package com.ndhzs.lib.config.sp

import android.content.Context
import android.content.SharedPreferences
import com.ndhzs.lib.config.ConfigApplicationWrapper.application

/**
 * ...
 *
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/8/9 15:25
 */

/**
 * 全局通用的 Sp，用于整个应用内传递数据，重要数据以及大量数据请不要使用该 SP 保存
 *
 * 请在下面写上传递的 key 值，以 SP_模块名_作用名 开头命名，后面还可以细分
 *
 * ## 注意：这个是给整个应用全局使用的！
 */
val defaultSp: SharedPreferences
  get() = application.getSharedPreferences("share_data", Context.MODE_PRIVATE)

const val SP_TEST_DEMO = "这只是一个用于演示的例子"