package com.ndhzs.lib.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/7/25 14:34
 */

fun ImageView.glide(url: String, func: (RequestBuilder<Drawable>.() -> Unit)? = null) {
  Glide.with(this)
    .load(url)
    .apply { func?.invoke(this) }
    .into(this)
}