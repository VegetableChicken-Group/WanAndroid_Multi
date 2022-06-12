package com.ndhzs.lib.web.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.ndhzs.lib.common.extensions.lazyUnlock
import com.ndhzs.lib.common.ui.BaseActivity
import com.ndhzs.lib.web.R
import com.ndhzs.lib.web.helper.popup


/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.lib.web.activity
 * @ClassName:      BaseWebViewActivity
 * @Author:         Yan
 * @CreateDate:     2022年05月31日 19:08:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    通用的网页加载页面
 */

class WebViewActivity : BaseActivity() {

    companion object {

        private const val webViewExtra = "WebViewParams"
        private const val titleExtra = "WanAndroid"

        //外部调用WebView的方法
        fun start(context: Context, url : String, title : String = titleExtra) {
            context.startActivity(
                Intent(context, WebViewActivity::class.java).apply {
                    putExtra(webViewExtra, url)
                    putExtra(titleExtra, title)
                }
            )
        }
    }

    private val url by lazyUnlock {
        intent.getStringExtra(webViewExtra)!!
    }

    private val webView: WebView by R.id.wv_web.view()
    private val textView : TextView by R.id.tv_web_title.view()
    private val backImage : ImageView by R.id.iv_web_back.view()
    private val moreImage : ImageView by R.id.iv_web_more.view()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //设置视图
        setContentView(R.layout.web_activity)

        //显示标题栏
        showStatusBar()

        //设置标题按钮
        setImageButton()

        //初始化WebView
        initWebView()
        webView.loadUrl(url)
    }

    /**
     * 设置标题栏按钮
     */
    private fun setImageButton(){
            //退出按钮
            backImage.apply{
                setOnClickListener {
                    onBackPressed()
                }
            }


            //更多按钮
            moreImage.apply {
                setOnClickListener {
                    showMenu(it)
                }
            }
    }

    /**
     * 弹出菜单
     */
    private fun showMenu(view : View){
        popup(view,R.menu.web_menu,
            {
                when(it.itemId){
                    R.id.action_share -> {
                        //分享网页
                        shareWeb()
                        true
                    }
                    R.id.action_open -> {
                        //开启网页
                        openWeb()
                        true
                    }
                    else -> false
                }
            })
    }

    /**
     * 分享
     */
    private fun shareWeb(){
        val sentIntent = Intent()
            .setAction(Intent.ACTION_SEND)
            .putExtra(Intent.EXTRA_TEXT,"玩Android分享【${textView.text}】：${webView.url}")
            .setType("text/plain")

        startActivity(Intent.createChooser(sentIntent,"分享"))
    }

    /**
     * 打开网页
     */
    private fun openWeb(){
        val url = Uri.parse(url)
        startActivity(Intent(Intent.ACTION_VIEW,url))
    }



    /**
     * 设置导航栏
     */
    private fun showStatusBar(){
        val window = this.window
        val decorView = window.decorView

        WindowCompat.setDecorFitsSystemWindows(window, true)
        val windowInsetsController = ViewCompat.getWindowInsetsController(decorView)
        windowInsetsController?.isAppearanceLightStatusBars = true // 设置状态栏字体颜色为黑色

        //设置状态栏背景颜色
        window.statusBarColor = ContextCompat.getColor(this, com.ndhzs.lib.common.R.color.status)
    }

    /**
     * 对WebView的初始化操作
     */
    private fun initWebView(){
        val settings: WebSettings = webView.settings

        // 支持JS
        true.also { settings.javaScriptEnabled = it }

        // 自适应屏幕
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true

        // 支持缩放
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true

        // 隐藏缩放控件
        settings.displayZoomControls = false

        // 开启Dom存储功能
        settings.domStorageEnabled = true

        // 开启文件访问
        settings.allowFileAccess = true
        settings.loadsImagesAutomatically = true
        settings.javaScriptCanOpenWindowsAutomatically = true

        // 设置缓存策略
        if (isConnected(this)) {
            settings.cacheMode = WebSettings.LOAD_DEFAULT
        } else {
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }

        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            // 页面开始加载时调用，如显示加载圈
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            // 页面加载完成时调用，如隐藏加载圈
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            // 页面出错时调用，可在此时加载失败时的页面
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
            }

        }

        webView.webChromeClient = object : WebChromeClient() {

            //获得WebView的标题
            override fun onReceivedTitle(view: WebView, webTitle: String) {
                super.onReceivedTitle(view, webTitle)
                textView.text = webTitle
            }

            // 获取页面加载的进度
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)

            }
        }
    }

    /**
     * 检查网络是否连接
     * @param context
     * @return
     */
    private fun isConnected(context: Context): Boolean {
        var isNetUsable = false
        val manager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = manager.getNetworkCapabilities(manager.activeNetwork)
            if (networkCapabilities != null) {
                isNetUsable =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            }
        }
        return isNetUsable
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        webView.removeAllViews()
        webView.destroy()
        super.onDestroy()
    }
}