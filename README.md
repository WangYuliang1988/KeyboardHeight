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
