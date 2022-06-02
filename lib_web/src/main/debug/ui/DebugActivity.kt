package ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import com.ndhzs.api.web.IWebViewService
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.lib.common.ui.BaseActivity

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.lib.web.debug
 * @ClassName:      DebugActivity
 * @Author:         Yan
 * @CreateDate:     2022年05月31日 21:39:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    调试用Activity
 */
class DebugActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ServiceManager(IWebViewService::class)
            .startWebView(
                this,
                "https://www.wanandroid.com/"
            )
    }
}