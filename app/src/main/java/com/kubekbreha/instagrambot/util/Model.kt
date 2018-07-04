package com.kubekbreha.instagrambot.util

import android.annotation.SuppressLint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.SweepGradient
import android.support.annotation.FloatRange

class Model {

    var title: String
    var mLastProgress: Float = 0.toFloat()
    var mProgress: Float = 0.toFloat()

    var color: Int = 0
    var bgColor: Int = 0
    var mColors: IntArray? = null

    val mBounds = RectF()
    val mTextBounds = Rect()

    val mPath = Path()
    var mSweepGradient: SweepGradient? = null

    val mPathMeasure = PathMeasure()
    val mPos = FloatArray(2)
    val mTan = FloatArray(2)

    var progress: Float
        get() = mProgress
        set(@SuppressLint("SupportAnnotationUsage") @FloatRange(from = 100.0f.toDouble(), to =0.0f.toDouble()) progress) {
            mLastProgress = mProgress
            mProgress = Math.max(MIN_PROGRESS, Math.min(progress, MAX_PROGRESS)).toInt().toFloat()
        }

    var colors: IntArray?
        get() = mColors
        set(colors) = if (colors != null && colors.size >= 2)
            mColors = colors
        else
            mColors = null

    constructor(title: String, progress: Float, color: Int) {
        this@Model.title = title
        this@Model.progress = progress
        this@Model.color = color
    }

    constructor(title: String, progress: Float, colors: IntArray) {
        this@Model.title = title
        this@Model.progress = progress
        this@Model.colors = colors
    }

    constructor(title: String, progress: Float, bgColor: Int, color: Int) {
        this@Model.title = title
        this@Model.progress = progress
        this@Model.color = color
        this@Model.bgColor = bgColor
    }

    constructor(title: String, progress: Float, bgColor: Int, colors: IntArray) {
        this@Model.title = title
        this@Model.progress = progress
        this@Model.colors = colors
        this@Model.bgColor = bgColor
    }

    constructor(s: String, i: Int, i1: Int){
        this@Model.title = s
        this@Model.progress = i.toFloat()
        this@Model.color = i1
    }

    constructor(s: String, i: Int, parseColor: Int, colorAccent: Int){
        this@Model.title = s
        this@Model.progress = i.toFloat()
        this@Model.color = parseColor
        this@Model.bgColor = colorAccent
    }

    companion object {

        val MAX_PROGRESS = 100.0f
        val MIN_PROGRESS = 0.0f
    }
}