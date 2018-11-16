BackgroundTransparentLayout
==============================================================

![image]()

#example

    <com.github.namelesspeople.backgroundtransparentlib.BackgroundTransparentLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:quadrant="topRight|bottomLeft"
        app:radius="20dp">
    <!-- 任意布局-->
        <ImageView
            android:id="@+id/image"
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:src="@color/colorPrimaryDark"/>

    </com.github.namelesspeople.backgroundtransparentlib.BackgroundTransparentLayout>

	1.默认是圆角矩形，4个角都存在
  2.将子布局边角遮盖
	3.quadrant有 topRight topLeft bottomLeft bottomRight四个方向上任意组合
	4.radius 任意圆角大小

	
[![](https://www.jitpack.io/v/NamelessPeople/BackgroundTransparentLayout.svg)](https://www.jitpack.io/#NamelessPeople/BackgroundTransparentLayout)

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        implementation 'com.github.NamelessPeople:BackgroundTransparentLayout:1.0'
	}

