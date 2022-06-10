package com.liudongcai.richtext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.liudongcai.richtext.view.CenterImageSpan

/**
 * 类描述：富文本
 * 创建人：刘栋财
 * 创建时间：2022/6/10 17:58
 */
class RichTextView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(
    context!!, attrs, defStyleAttr
) {

    var contentBuilder = SpannableStringBuilder()
    var isTextEnd = false

    init {
        contentBuilder = SpannableStringBuilder()
        isTextEnd = false
    }

    // Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括，即在指定范围的前面和后面插入新字符都不会应用新样式
    // Spannable.SPAN_EXCLUSIVE_INCLUSIVE ：前面不包括，后面包括。即仅在范围字符的后面插入新字符时会应用新样式
    // Spannable.SPAN_INCLUSIVE_EXCLUSIVE ：前面包括，后面不包括。
    // Spannable.SPAN_INCLUSIVE_INCLUSIVE ：前后都包括。

    /**
     * 添加文本 加粗、下划线、点击事件采用具名参数，有需要的时候调用，下同
     */
    @JvmOverloads
    fun addText(
        text: String,
        textsSize: Float,
        textColor: Int,
        isBold: Boolean = false,
        isUnderline: Boolean = false
    ): RichTextView {
        val spanString = SpannableString(text)

        //字体颜色
        val spanColor = ForegroundColorSpan(ContextCompat.getColor(context, textColor))
        spanString.setSpan(spanColor, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        if (isUnderline) {
            //下划线
            val spanUnderline = UnderlineSpan()
            spanString.setSpan(spanUnderline, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        //字体大小
        val spanSize = AbsoluteSizeSpan(spToPx(context, textsSize))
        spanString.setSpan(spanSize, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        if (isBold) {
            //加粗
            val spanbold = StyleSpan(Typeface.BOLD)
            spanString.setSpan(spanbold, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        contentBuilder.append(spanString)
        isTextEnd = true
        return this
    }

    /**
     * 添加文本 加粗、下划线、点击事件采用具名参数，有需要的时候调用，下同，带点击事件
     */
    @JvmOverloads
    fun addText(
        text: String,
        textsSize: Float,
        textColor: Int,
        isBold: Boolean = false,
        isUnderline: Boolean = false,
        handleClick: () -> Unit
    ): RichTextView {
        val spanString = SpannableString(text)

        //字体大小
        val spanSize = AbsoluteSizeSpan(spToPx(context, textsSize))
        spanString.setSpan(spanSize, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        if (isBold) {
            //加粗
            val spanbold = StyleSpan(Typeface.BOLD)
            spanString.setSpan(spanbold, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val spanClick = object : ClickableSpan() {
            override fun onClick(p0: View) {
                //点击事件
                handleClick()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                //字体颜色
                ds.color = ContextCompat.getColor(context, textColor)
                //下划线
                ds.isUnderlineText = isUnderline
            }
        }
        spanString.setSpan(spanClick, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        contentBuilder.append(spanString)
        isTextEnd = true
        //可点击
        movementMethod = LinkMovementMethod.getInstance()
        return this
    }

    /**
     * 添加标签文本
     */
    @JvmOverloads
    fun addTagText(
        text: String,
        textsSize: Float,
        textColor: Int,
        background: Int,
        isBold: Boolean = false,
        isUnderline: Boolean = false
    ): RichTextView {
        val spanString = SpannableString(text)

        //  设置标签的布局
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.layout_richtextview_tag, null)
        val tv_tag = view.findViewById<TextView>(R.id.tv_tag)
        tv_tag?.text = text
        tv_tag?.textSize = textsSize
        tv_tag?.setTextColor(ContextCompat.getColor(context, textColor))
        tv_tag?.setBackgroundResource(background)
        if (isBold) {
            //加粗
            tv_tag?.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }
        if (isUnderline) {
            //下划线
            tv_tag?.paint?.flags = Paint.UNDERLINE_TEXT_FLAG
            tv_tag?.paint?.isAntiAlias = true
        }

        val bitmap: Bitmap? = convertViewToBitmap(view)
        if (bitmap != null) {
            val drawable: Drawable = BitmapDrawable(bitmap)
            drawable.setBounds(0, 0, tv_tag.width, tv_tag.height)

            val span = CenterImageSpan(drawable)
            spanString.setSpan(span, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        contentBuilder.append(spanString)
        isTextEnd = false
        return this
    }

    /**
     * 添加标签文本
     */
    @JvmOverloads
    fun addTagText(
        text: String,
        textsSize: Float,
        textColor: Int,
        background: Int,
        isBold: Boolean = false,
        isUnderline: Boolean = false,
        handleClick: () -> Unit
    ): RichTextView {
        val spanString = SpannableString(text)

        //  设置标签的布局
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.layout_richtextview_tag, null)
        val tv_tag = view.findViewById<TextView>(R.id.tv_tag)
        tv_tag?.text = text
        tv_tag?.textSize = textsSize
        tv_tag?.setTextColor(ContextCompat.getColor(context, textColor))
        tv_tag?.setBackgroundResource(background)
        if (isBold) {
            //加粗
            tv_tag?.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }
        if (isUnderline) {
            //下划线
            tv_tag?.paint?.flags = Paint.UNDERLINE_TEXT_FLAG
            tv_tag?.paint?.isAntiAlias = true
        }

        val bitmap: Bitmap? = convertViewToBitmap(view)
        if (bitmap != null) {
            val drawable: Drawable = BitmapDrawable(bitmap)
            drawable.setBounds(0, 0, tv_tag.width, tv_tag.height)

            val span = CenterImageSpan(drawable)
            spanString.setSpan(span, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        //点击事件
        val spanClick = object : ClickableSpan() {
            override fun onClick(p0: View) {
                handleClick()
            }
        }
        spanString.setSpan(spanClick, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        contentBuilder.append(spanString)
        isTextEnd = false
        //可点击
        movementMethod = LinkMovementMethod.getInstance()
        return this
    }

    /**
     * 添加图片
     */
    @JvmOverloads
    fun addImage(resId: Int, width: Float, height: Float): RichTextView {
        val spanString = SpannableString("**")
        val bitmap = BitmapFactory.decodeResource(resources, resId)
        val drawable: Drawable = BitmapDrawable(bitmap)
        drawable.setBounds(0, 0, dpToPx(context, width), dpToPx(context, height))
        val span = CenterImageSpan(drawable)
        spanString.setSpan(span, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        contentBuilder.append(spanString)
        isTextEnd = false
        return this
    }

    /**
     * 添加图片
     */
    @JvmOverloads
    fun addImage(resId: Int, width: Float, height: Float, handleClick: () -> Unit): RichTextView {
        val spanString = SpannableString("**")
        val bitmap = BitmapFactory.decodeResource(resources, resId)
        val drawable: Drawable = BitmapDrawable(bitmap)
        drawable.setBounds(0, 0, dpToPx(context, width), dpToPx(context, height))
        val span = CenterImageSpan(drawable)
        spanString.setSpan(span, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        //点击事件
        val spanClick = object : ClickableSpan() {
            override fun onClick(p0: View) {
                handleClick()
            }
        }
        spanString.setSpan(spanClick, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        contentBuilder.append(spanString)
        isTextEnd = false
        //可点击
        movementMethod = LinkMovementMethod.getInstance()
        return this
    }

    /**
     * 添加空格
     */
    fun addEmptyText(text: String): RichTextView {
        val spanString = SpannableString(text)
        contentBuilder.append(spanString)
        return this
    }

    /**
     * 清除所有已设置的样式和文字
     */
    fun clear(): RichTextView {
        contentBuilder?.clear()
        return this
    }

    /**
     * View转bitmap
     */
    private fun convertViewToBitmap(view: View): Bitmap? {
        view.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        return view.drawingCache
    }

    /**
     * 建造富文本
     */
    fun build() {
        if (!isTextEnd) {
            //不是以文本结束，加上一个空格，不然图片结尾会变形
            addEmptyText(" ")
        }
        text = SpannableString(contentBuilder)
        gravity = Gravity.CENTER_VERTICAL
        //点击背景色透明
        highlightColor = ContextCompat.getColor(context, R.color.color_00000000)
    }

    /**
     * 方法描述：DP转PX
     */
    private fun dpToPx(context: Context?, dpValue: Float): Int {
        if (context == null) {
            return (dpValue * 2 + 0.5f).toInt()
        }
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 方法描述：将SP值转换为PX值，保证文字大小不变
     */
    private fun spToPx(context: Context?, spValue: Float): Int {
        if (context == null) {
            return (spValue * 2 + 0.5f).toInt()
        }
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

}