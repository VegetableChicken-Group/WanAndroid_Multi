package com.ndhzs.module.main.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ndhzs.api.account.IAccountService
import com.ndhzs.lib.common.config.HOME_PAGE
import com.ndhzs.lib.common.config.LOGIN_ENTRY
import com.ndhzs.lib.common.extensions.gone
import com.ndhzs.lib.common.extensions.setOnSingleClickListener
import com.ndhzs.lib.common.extensions.toast
import com.ndhzs.lib.common.extensions.visible
import com.ndhzs.lib.common.service.ServiceManager
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindActivity
import com.ndhzs.module.main.IMainService
import com.ndhzs.module.main.R
import com.ndhzs.module.main.databinding.MainActivityMainBinding
import com.ndhzs.module.main.service.MainService
import com.ndhzs.module.main.ui.adapter.MainVpAdapter
import com.ndhzs.module.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : BaseVmBindActivity<MainViewModel, MainActivityMainBinding>(isCancelStatusBar = false) {

    // 在这里填入对应页面fragment的route
    private val pageRoutes = hashMapOf(
        0 to HOME_PAGE,
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }
        // BottomNav
        binding.bottomNav.apply {
            labelVisibilityMode = BottomNavigationView.LABEL_VISIBILITY_LABELED
            setOnItemSelectedListener {
                if (binding.vp2Main.scrollState != ViewPager2.SCROLL_STATE_IDLE) return@setOnItemSelectedListener false
                when (it.itemId) {
                    R.id.bottom_nav_home -> binding.vp2Main.currentItem = 0
                    R.id.bottom_nav_marketplace -> binding.vp2Main.currentItem = 1
                    R.id.bottom_nav_wechat -> binding.vp2Main.currentItem = 2
                    R.id.bottom_nav_system -> binding.vp2Main.currentItem = 3
                    R.id.bottom_nav_project -> binding.vp2Main.currentItem = 4
                }
                true
            }
        }
        binding.vp2Main.adapter = MainVpAdapter(supportFragmentManager, lifecycle) {
            ServiceManager.fragment(pageRoutes[it] ?: error("state error"))
        }
        binding.vp2Main.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                MainService.actionMap[pageRoutes[position] ?: error("state error")]?.invoke(supportActionBar!!)
            }
        })
        binding.floatingActionBtn.setOnSingleClickListener {
            lifecycleScope.launch {
                ServiceManager
                    .invoke(IMainService::class)
                    .fabClickState
                    .emit(Unit)
            }
        }
        // 初次注册时上面获取不到action，所以在注册时回调action
        MainService.actionBarActionFlow.collectLaunch {
            supportActionBar?.it()
        }
        binding.navView.getHeaderView(0)
            .apply {
                val avatar = findViewById<ImageView>(R.id.siv_user)
                val name = findViewById<TextView>(R.id.tv_user)
                val tvInfo = findViewById<TextView>(R.id.tv_info)
                val service = ServiceManager.invoke(IAccountService::class)
                avatar.setOnClickListener {
                    Log.d("Test", "(MainActivity.kt:87) ==> ${service.isLogin()}")
                    ServiceManager.activity(LOGIN_ENTRY)
                }
                service
                    .observeUserInfo(this@MainActivity)
                    .safeSubscribeBy {
                        val res = it.getOrNull()
                        if (res == null) {
                            name.text = "尚未登录"
                            tvInfo.gone()
                        } else {
                            viewModel.getUserInfo().safeSubscribeBy { r ->
                                r.throwApiExceptionIfFail()
                                val data = r.data
                                name.text = res.publicName
                                tvInfo.visible()
                                tvInfo.text = "等级:${data.coinInfo?.level} 排名:${data.coinInfo?.rank}"
                            }
                        }
                    }
            }
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout -> {
                    if (!ServiceManager.invoke(IAccountService::class).isLogin()) {
                        ServiceManager.invoke(IAccountService::class)
                            .logout().safeSubscribeBy {
                                "登出成功".toast()
                            }
                    }
                }
            }
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}