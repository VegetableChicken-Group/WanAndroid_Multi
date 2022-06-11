package com.ndhzs.module.main.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

/**
 * kim.bifrost.coldrain.wanandroid.widget.BottomNavigationBehavior
 * WanAndroid
 * 代码来自 iceCola7/WanAndroid
 * 我目前还不会写协调者布局的行为
 *
 * @author 寒雨
 * @since 2021/12/5 13:41
 **/
class BottomNavigationBehavior(context: Context, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    private var outAnimator: ObjectAnimator? = null
    private var inAnimator: ObjectAnimator? = null

    // 垂直滑动
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (dy > 0) { // 上滑隐藏
            if (outAnimator == null) {
                outAnimator =
                    ObjectAnimator.ofFloat(child, "translationY", 0f, child.height.toFloat())
                outAnimator?.duration = 200
            }
            if (!outAnimator!!.isRunning && child.translationY <= 0) {
                outAnimator!!.start()
            }
        } else if (dy < 0) { // 下滑显示
            if (inAnimator == null) {
                inAnimator =
                    ObjectAnimator.ofFloat(child, "translationY", child.height.toFloat(), 0f)
                inAnimator?.duration = 200
            }
            if (!inAnimator!!.isRunning && child.translationY >= child.height) {
                inAnimator!!.start()
            }
        }
    }
}