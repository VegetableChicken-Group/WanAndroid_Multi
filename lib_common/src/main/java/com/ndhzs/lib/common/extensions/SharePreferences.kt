package com.ndhzs.lib.common.extensions

import android.content.Context
import android.content.SharedPreferences

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/30 16:09
 */

/**
 * 快捷得到方式
 */
fun Context.getSp(name: String): SharedPreferences {
  return getSharedPreferences(name, Context.MODE_PRIVATE)
}

/**
 * 全局通用的 Sp，用于整个应用内传递数据，重要数据以及大量数据请不要使用该 SP 保存
 *
 * 请在下面写上传递的 key 值
 *
 * 注意：这个是给全局使用的！
 */
val Context.defaultSp
  get() = getSharedPreferences("defaultSp", Context.MODE_PRIVATE)

