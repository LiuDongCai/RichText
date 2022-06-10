package com.liudongcai.richtext.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan

/**
 * 类描述：解决图文混排，图片和文字居中对齐问题
 * 创建人：刘栋财
 * 创建时间：2022/6/10 17:58
 */
class CenterImageSpan : ImageSpan {

    constructor(drawable: Drawable?) : super(drawable!!) {}
    constructor(bitmap: Bitmap?) : super(bitmap!!) {}

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int,
                      paint: Paint) {
        val b = drawable
        val fm = paint.fontMetricsInt
        val transY = (y + fm.descent + y + fm.ascent) / 2 - b.bounds.bottom / 2 //计算y方向的位移
        canvas.save()
        canvas.translate(x, transY.toFloat()) //绘制图片位移一段距离
        b.draw(canvas)
        canvas.restore()
    }
}