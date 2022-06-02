package com.ndhzs.module.home.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.view.postDelayed
import androidx.viewpager2.widget.ViewPager2
import com.ndhzs.lib.common.extensions.dp2px
import com.ndhzs.module.home.R

/**
 * com.ndhzs.module.home.widget.IndicatorView
 * WanAndroid_Multi
 *
 * @author 寒雨
 * @since 2022/5/31 21:33
 **/
class IndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
    }
    private var focusColor = Color.parseColor("#ffffff")
    private var unfocusedColor = Color.parseColor("#E0E0E0")
    private var dotBetweenDistance = 0f

    private var firstDotX: Float = 0f
    private var firstDotY: Float = 0f
    private var radius: Float = 0f

    private var dotCount = 5
        set(value) {
            field = value
            invalidate()
        }

    var currentFocusPosition = 1
        set(value) {
            field = value
            // 重绘
            invalidate()
        }

    init {
        if (attrs != null) {
            val ty = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView)
            focusColor = ty.getColor(
                R.styleable.IndicatorView_focus_color,
                focusColor
            )
            unfocusedColor = ty.getColor(
                R.styleable.IndicatorView_unfocused_color,
                unfocusedColor
            )
            dotBetweenDistance = ty.getDimension(
                R.styleable.IndicatorView_dot_between_distance,
                dotBetweenDistance
            )
            ty.recycle()
        }
    }

    fun bindBannerVp(viewPager2: ViewPager2, dataSize: Int) {
        viewPager2.postDelayed(100) {
            dotCount = dataSize
            viewPager2.registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        currentFocusPosition = position % dataSize + 1
                    }
                }
            )
            requestLayout()
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        var width = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        radius = height / 2.toFloat()
        firstDotX = x + radius
        firstDotY = y + height / 2
        // wrap_content 自行计算宽度
        if (widthMode == MeasureSpec.AT_MOST) {
            width = (dotCount * radius * 2F + dotBetweenDistance * (dotCount - 1)).toInt()
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        paint.color = unfocusedColor
        // 绘制点
        var x = firstDotX
        repeat(dotCount) {
            if (it + 1 == currentFocusPosition) {
                paint.color = focusColor
            }
            canvas.drawCircle(x, firstDotY, radius, paint)
            paint.color = unfocusedColor
            x += dotBetweenDistance + 2 * radius
        }
    }
}