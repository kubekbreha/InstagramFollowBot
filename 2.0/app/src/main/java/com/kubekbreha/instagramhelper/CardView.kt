package com.kubekbreha.instagramhelper

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build
import android.support.annotation.ArrayRes
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout


class CardView : LinearLayout {

    private var gradientPaint: Paint? = null
    private var currentGradient: IntArray? = null


    private var evaluator: ArgbEvaluator? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        evaluator = ArgbEvaluator()

        gradientPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        setWillNotDraw(false)

        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL

    }

    private fun initGradient() {
        val centerX = width * 0.5f
        val gradient = LinearGradient(
                centerX, 0f, centerX, height.toFloat(),
                currentGradient!!, null,
                Shader.TileMode.MIRROR)
        gradientPaint!!.shader = gradient
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (currentGradient != null) {
            initGradient()
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), gradientPaint!!)
        super.onDraw(canvas)
    }

    fun setForecast(oneList: UsersListItem) {
        val gradient = oneList.gradient
        currentGradient = getGradient(gradient)
        if (width != 0 && height != 0) {
            initGradient()
        }

        invalidate()


    }

    fun onScroll(fraction: Float, oldF: UsersListItem, newF: UsersListItem) {
        currentGradient = mix(fraction,
                getGradient(newF.gradient),
                getGradient(oldF.gradient))
        initGradient()
        invalidate()
    }

    private fun mix(fraction: Float, c1: IntArray, c2: IntArray): IntArray {
        return intArrayOf(evaluator!!.evaluate(fraction, c1[0], c2[0]) as Int, evaluator!!.evaluate(fraction, c1[1], c2[1]) as Int, evaluator!!.evaluate(fraction, c1[2], c2[2]) as Int)
    }

    private fun getGradient(number: Int): IntArray {
        return when (number) {
            0 -> colors(R.array.gradientPeriodicClouds)
            1 -> colors(R.array.gradientCloudy)
            2 -> colors(R.array.gradientMostlyCloudy)
            3 -> colors(R.array.gradientPartlyCloudy)
            4 -> colors(R.array.gradientClear)
            else -> throw IllegalArgumentException()
        }
    }


    private fun colors(@ArrayRes res: Int): IntArray {
        return context.resources.getIntArray(res)
    }

}
