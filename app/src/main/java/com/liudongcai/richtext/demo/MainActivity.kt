package com.liudongcai.richtext.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.liudongcai.richtext.RichTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RichTextView>(R.id.rtv_text)?.clear()
            ?.addText("微软IE浏览器",14f, R.color.red)
            ?.addText("将于当地时间6月15日被永久关闭",14f, R.color.black)
            ?.addText("永久关闭",16f, R.color.red,isBold = true,isUnderline = true){
                //点击事件
                Toast.makeText(this,"永久关闭",Toast.LENGTH_SHORT).show()
            }
            ?.build()

        findViewById<RichTextView>(R.id.rtv_text2)
            ?.addTagText("热门",14f, R.color.color_126aff, R.color.color_d9e8ff)
            ?.addEmptyText(" ")
            ?.addText("芬兰和瑞典玩砸了！加入北约无望，又激怒俄罗斯？土耳其下手够狠！",14f, R.color.black)
            ?.build()

        findViewById<RichTextView>(R.id.rtv_text3)
            ?.addImage(R.mipmap.vip,18f,18f) {
                Toast.makeText(this,"Click：image",Toast.LENGTH_SHORT).show()
            }
            ?.addEmptyText(" ")
            ?.addText("亚马逊云科技提供全球覆盖广泛、服务深入的云平台。12个月免费套餐服务，允许客户从账户创建之日起一年内在指定限制内免费使用该产品。",14f,
                R.color.black)
            ?.build()
    }
}