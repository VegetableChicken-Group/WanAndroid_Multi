package com.ndhzs.lib.utils.extensions

import android.view.View

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/3/24 23:08
 */

fun View.gone(): View {
  visibility = View.GONE
  return this
}

fun View.invisible(): View {
  visibility = View.INVISIBLE
  return this
}

fun View.visible(): View {
  visibility = View.VISIBLE
  return this
}

/**
 * @param interval 毫秒为单位，点击间隔小于这个值监听事件无法生效
 * @param click 具体的点击事件
 */
fun View.setOnSingleClickListener(interval: Long = 500, click: (View) -> Unit) {
  setOnClickListener {
    val tag = getTag(423612342) as? Long
    if (System.currentTimeMillis() - (tag ?: 0L) > interval) {
      click(it)
    }
    it.setTag(423612342, System.currentTimeMillis())
  }
}