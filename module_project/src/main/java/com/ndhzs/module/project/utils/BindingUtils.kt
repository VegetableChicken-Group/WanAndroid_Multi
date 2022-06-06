package com.ndhzs.module.project.utils

import android.os.Build
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.ndhzs.lib.common.extensions.appContext
import com.ndhzs.module.test.R


/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.module.project.utils
 * @ClassName:      BindingUtils
 * @Author:         Yan
 * @CreateDate:     2022年06月06日 21:03:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    用于 DataBinding 绑定数据的工具类
 */

val imageLoader = ImageLoader.Builder(appContext)
    .components {
        if (Build.VERSION.SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }
.build()

/**
 * 加载完整image
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {

    view.load(url, imageLoader){
        crossfade(200)
        placeholder(R.drawable.default_project_img)
    }

//    view.load(""http://www.wanandroid.com/blogimgs/ca8471ed-1781-4c25-80d0-cec10c40d66d.png"")
//    view.load("http://www.wanandroid.com/blogimgs/d0c80e9c-b063-42f4-80f3-4e104193ea14.gif")

}
