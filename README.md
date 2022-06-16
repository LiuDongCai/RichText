# RichText

* 富文本开发中经常用到，每次开发都要写一堆代码。现在网上封装的都很臃肿而且不太好用。这是全网最好用的富文本控件，支持各种富文本样式以及点击事件。

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
   	implementation 'com.github.LiuDongCai:RichText:master-SNAPSHOT'
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
   
       <com.liudongcai.richtext.RichTextView
           android:id="@+id/rtv_text2"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_marginTop="10dp"/>
   
       <com.liudongcai.richtext.RichTextView
           android:id="@+id/rtv_text3"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_marginTop="10dp"/>
   ```

2. 赋值

    ```
     findViewById<RichTextView>(R.id.rtv_text)?.clear()
         ?.addText("微软IE浏览器",14f,R.color.red)
         ?.addText("将于当地时间6月15日被永久关闭",14f,R.color.black)
         ?.addText("永久关闭",16f,R.color.red,isBold = true,isUnderline = true){
             //点击事件
             Toast.makeText(this,"永久关闭",Toast.LENGTH_SHORT).show()
         }
         ?.build()

     findViewById<RichTextView>(R.id.rtv_text2)
         ?.addTagText("热门",14f,R.color.color_126aff,R.color.color_d9e8ff)
         ?.addEmptyText(" ")
         ?.addText("芬兰和瑞典玩砸了！加入北约无望，又激怒俄罗斯？土耳其下手够狠！",14f,R.color.black)
         ?.build()

     findViewById<RichTextView>(R.id.rtv_text3)
         ?.addImage(R.mipmap.vip,18f,18f) {
             Toast.makeText(this,"Click：image",Toast.LENGTH_SHORT).show()
         }
         ?.addEmptyText(" ")
         ?.addText("亚马逊云科技提供全球覆盖广泛、服务深入的云平台。12个月免费套餐服务，允许客户从账户创建之日起一年内在指定限制内免费使用该产品。",14f,R.color.black)
         ?.build()
   ```

效果如下图所示：

![](/assets/richtext.gif)
