package com.liudongcai.richtext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RichTextView>(R.id.rtv_text)?.clear()
            ?.addText("Text",14f,R.color.purple_200)
            ?.addText("UnderLine_Text",16f,R.color.teal_200,isBold = true){
                Toast.makeText(this,"Click：UnderLine_Text",Toast.LENGTH_SHORT).show()
            }
            ?.addText("Bold_Text",16f,R.color.teal_200,isBold = true)
            ?.addEmptyText(" ")
            ?.addTagText("tag",16f,R.color.white,R.color.purple_200){
                Toast.makeText(this,"Click：tag",Toast.LENGTH_SHORT).show()
            }
            ?.addEmptyText(" ")
            ?.addImage(R.mipmap.image,100f,100f) {
                Toast.makeText(this,"Click：image",Toast.LENGTH_SHORT).show()
            }
            ?.build()
    }
}