# RichText

* 全网最好用的富文本控件，支持各种富文本样式以及点击事件。

## 添加依赖

1. 在根目录的build.gradle文件中添加jitpack仓库

   ```groovy
   allprojects {
   		repositories {
   			maven { url 'https://jitpack.io' }
   		}
   }
   ```

2. 添加**RichText**依赖

   ```groovy
   dependencies {
   	compile 'com.github.easilycoder:GuideView:0.0.1'
   }
   ```

## API

| 方法名 | 方法说明 | 备注 |
| ------------ | ------------- |  ------------- | 
| addText()| 添加文本 | text、textSize、textColor必选；isBold、isUnderline、handleClick可选 |
| addTagText()| 添加标签文本 | text、textSize、textColor、background必选；isBold、isUnderline、handleClick可选 |
| addImage()| 添加文本 | resId、width、height必选；handleClick可选 |
| addEmptyText()| 添加空格 | text必选 |
| clear()| 清除所有已设置的样式和文字 | 无 |
| build()| 建造富文本 | 无 |

## 示例

1. 在xml布局文件里添加RichTextView

    ```
      <com.liudongcai.richtext.RichTextView 
         android:id="@+id/rtv_text"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content" />
   ```

2. 赋值

    ```
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
   ```

效果如下图所示：

![](/assets/guideview.gif)
