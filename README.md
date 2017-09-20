# ganhuo
阅读技术前沿资讯
## 本app用的接口是来自于干货集中营(感谢提供api):http://gank.io/api
>1.首页视图是用tablayout+viewpager+fragment实现,特别的简单粗暴,首页的item其实是请求了两个①文章②图片,有机组合配套。
---
>2.利用了markdownview去加载了含有markdown语法的文本
---
![Image text](https://raw.githubusercontent.com/Aoyihala/img/master/ganhuo/home.gif)
---
>3.福利界面预览
---
![Image text](https://raw.githubusercontent.com/Aoyihala/img/master/ganhuo/fuli.gif)
---
![Image text](https://raw.githubusercontent.com/Aoyihala/img/master/ganhuo/fuli2.gif)
---
>4.另外还有收藏功能,分享功能待申请。
---
>5.利用的依赖
---
<pre><code>
    compile files('libs/fastjson-1.1.61.android.jar')
    compile 'com.android.support:appcompat-v7:26.+'
    compile 'com.android.support:cardview-v7:26.0.0-alpha1'
    compile 'com.android.support:design:26.0.0-alpha1'
    compile 'com.android.support:appcompat-v7:26.+'
    compile 'com.android.support:design:26.0.0-alpha1'
    compile 'com.android.support:cardview-v7:26.0.0-alpha1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.github.bumptech.glide:glide:4.0.0'
    compile 'com.github.githubwing:ByeBurger:1.2.3'
    compile 'com.github.hackware1993:MagicIndicator:1.5.0'
    compile 'com.jakewharton:butterknife:7.0.1'
    compile 'de.hdodenhof:circleimageview:2.1.0'
    compile 'com.zhy:okhttputils:2.6.2'
    compile 'com.github.markushi:android-ui:1.2'
    compile 'com.github.mukeshsolanki:MarkdownView-Android:1.0.4'
    compile 'com.trycatch.android:mysnackbar:1.2.2'
    compile 'org.greenrobot:greendao:3.2.0'
    compile 'com.github.donkingliang:GroupedRecyclerViewAdapter:1.0.1'
    testCompile 'junit:junit:4.12'
    compile 'pub.devrel:easypermissions:0.4.2'
</pre></code>
# 最后感谢新浪微博的@熊猫大侠,给我的app提供了灵感。希望以后能做出更多漂亮实用的md app。
