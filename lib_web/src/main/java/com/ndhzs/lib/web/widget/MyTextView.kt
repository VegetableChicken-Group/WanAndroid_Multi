package com.ndhzs.lib.web.widget

import android.content.Context
import android.util.AttributeSet

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.lib.web
 * @ClassName:      MyTextView
 * @Author:         Yan
 * @CreateDate:     2022年06月04日 02:05:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    解决TextView跑马灯失效的问题(可能是WebView和覆盖了TextView的焦距？)
 */

class MyTextView @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
        : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr){
    override fun isFocused(): Boolean {
        return true
    }
}