package com.ndhzs.module.system.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

/**
 * description ： 为了解决在vp中嵌套Rv引起的Rv中的item接收不到点击事件，所以用此view代替item中的TextView
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/6/10 21:03
 */
class Article(context: Context, attrs: AttributeSet?):
    androidx.appcompat.widget.AppCompatTextView(context!!, attrs) {
    var click:(()->Unit)? = null
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEvent.ACTION_DOWN-> {
                return true
            }
            MotionEvent.ACTION_UP->{
                click?.invoke()
                return false
            }
        }
        return super.dispatchTouchEvent(event)
    }
}