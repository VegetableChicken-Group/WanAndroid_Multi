package com.ndhzs.api.web

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.api.web
 * @ClassName:      IWebViewService
 * @Author:         Yan
 * @CreateDate:     2022年05月31日 18:41:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    WebView与外部通信的接口
 */
interface IWebViewService : IProvider {

    /**
     * 传入url的值来开启WebView
     */
    fun startWebView(context: Context,url : String)

}