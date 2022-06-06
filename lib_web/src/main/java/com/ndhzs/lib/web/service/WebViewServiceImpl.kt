package com.ndhzs.lib.web.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.api.web.IWebViewService
import com.ndhzs.lib.web.activity.WebViewActivity
import com.ndhzs.lib.common.config.WEB_SERVICE

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.lib.web
 * @ClassName:      WebViewServiceImpl
 * @Author:         Yan
 * @CreateDate:     2022年05月31日 18:55:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    WebViewService接口的具体实现类
 */

@Route(path = WEB_SERVICE)
class WebViewServiceImpl : IWebViewService{

    override fun startWebView(context: Context, url: String) {
        WebViewActivity.start(context = context, url = url)
    }

    override fun init(context: Context) {
    }
}