package com.ndhzs.module.home.utils

import android.text.Html
import android.text.Spanned

/**
 * com.ndhzs.module.home.utils.Text
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/6/2 1:34
 **/
fun String.htmlDecode(): Spanned = Html.fromHtml(this)