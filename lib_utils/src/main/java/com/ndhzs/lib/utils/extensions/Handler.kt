package com.ndhzs.lib.utils.extensions

import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed

/**
 * .
 *
 * @author 985892345
 * 2023/4/15 23:28
 */

/**
 * 全局 Handler 示例
 */
val defaultHandler = Handler(Looper.getMainLooper())

/**
 * 把时间写在前面的 post
 *
 * 当然你也可以用官方写的 [androidx.core.os.postDelayed]，只是这个名字因为与 [Handler.postDelayed] 一样所以不是很好用，
 * 因此我单独写了这个 postDelay
 */
fun Handler.postDelay(delayInMillis: Long, run: Runnable) {
  postDelayed(run, delayInMillis)
}