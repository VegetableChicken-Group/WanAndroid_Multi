package com.jwzg.lib.web.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.jwzg.api.web.IWebViewService
import com.jwzg.lib.web.activity.BaseWebViewActivity
import com.ndhzs.lib.common.config.WEB_SERVICE

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.jwzg.lib.web
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
        BaseWebViewActivity.start(context = context,url = url)
    }

    override fun init(context: Context?) {
    }


}