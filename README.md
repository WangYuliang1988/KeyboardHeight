Android 监听软键盘显示、隐藏，获取软键盘高度示例工程。

核心代码参考：<https://github.com/siebeprojects/samples-keyboardheight>。

### 环境说明
Android Studio 4.0.1，编译环境：API 29，验证系统：Android 10。

### 实现思路
创建一个PopupWindow，高度MATCH_PARENT，宽度为0，然后通过viewTreeObserver.addOnGlobalLayoutListener监听这个PopupWindow的布局变化，以此来实现对软键盘显示、隐藏的禁停，并计算软键盘高度。

### 工程说明
1. 核心代码：KeyboardHeightObserver.kt、KeyboardHeightProvider.kt；
2. Nt开头的Activity，分别对应原生界面在设置不同的windowSoftInputMode时，对软键盘的监听测试；
3. Web开头的Activity，分别对应WebView加载HTML时，在设置不同的windowSoftInputMode的情况下，对软键盘的监听测试；
4. 通过对AndroidManifest中theme进行切换，对同一个Activity在普通状态栏和沉浸式状态栏两种情况下，对软键盘进行监听测试。

### 图形概念
- 坐标系
  
  Android中有两种坐标系，分别是**Android坐标系**和**视图坐标系**。
  
  **Android坐标系**：在Android坐标系中，原点就是屏幕左上角顶点，从这个点向右为X轴正方向，向下为Y轴正方向。
  
  ![Android坐标系](/app/src/main/assets/Android坐标系.jpg)
  
  **视图坐标系**：视图坐标系描述的是子View在父View中的位置。视图坐标系中同样是以原点向右为X轴正方向，原点向下为Y轴正方向，只是**原点的位置不再是屏幕的左上角顶点，而是父View的左上角**。
  
  ![视图坐标系](/app/src/main/assets/视图坐标系.jpg)
  
- Point
  
  Point是用来描述**点**的类，包含两个属性，分别是x坐标和y坐标。Point和PointF的区别为，Point坐标值为整型，PointF坐标值为浮点型。
  
- Rect
  
  Rect是用来描述**矩形**的类，包含四个属性，分别是左、上、右、下四个坐标，其中左、上两个坐标定义了矩形的左上角顶点，右、下两个坐标定义了矩形的右下角顶点，通过这两个顶点确定了矩形的范围。Rect和RectF的区别为，Rect坐标值为整型，RectF坐标值为浮点型。

### 视图结构
- 结构概览
  
  ![Android视图结构](/app/src/main/assets/view_architecture.png)
  
- Activity
  
  Activity并不直接负责控制视图，真正控制视图的是Window。Window是一个抽象类，Activity持有的是PhoneWindow实例。
  
- Window
  
  Android中的所有视图，不论是Activity，Dialog还是Toast，都是通过Window展示的。Window有三种类型，分别是：APPLICATION_WINDOW、SUB_WINDOW、SYSTEM_WINDOW，APPLICATION_WINDOW对应Activity；SUB_WINDOW如Dialog，不能单独存在，需要附属在特定的父Window上；SYSTEM_WINDOW如Toast、状态栏。Window内部持有一个DecorView，这个DecorView是所有View的根布局。
  
- DecorView
  
  DecorView是Window的根视图，继承自FrameLayout，其内部包含一个LinearLayout，LinearLayout中包括TitleView和ContentView两块区域。TitleView区域用于显示ActionBar，ContentView区域用于展示实际定义的Activity布局。
  
- ViewRoot
  
  ViewRoot不属于View树，它既非View的子类，也非View的父类。ViewRoot像个连接器，连接WindowManagerService和DecorView，完成View的绘制（measure、layout、draw）。
