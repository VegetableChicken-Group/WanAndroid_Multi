package com.ndhzs.module.main.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.ndhzs.lib.common.config.HOME_PAGE
import com.ndhzs.lib.common.extensions.setOnSingleClickListener
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindActivity
import com.ndhzs.module.main.IMainService
import com.ndhzs.module.main.R
import com.ndhzs.module.main.databinding.MainActivityMainBinding
import com.ndhzs.module.main.ui.adapter.MainVpAdapter
import com.ndhzs.module.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : BaseVmBindActivity<MainViewModel, MainActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        binding.vp2Main.adapter = MainVpAdapter(supportFragmentManager, lifecycle) {
            when (it) {
                0 -> ARouter.getInstance().build(HOME_PAGE).navigation() as Fragment
                else -> error("state error")
            }
        }
        binding.floatingActionBtn.setOnSingleClickListener {
            lifecycleScope.launch {
                ARouter.getInstance()
                    .navigation(IMainService::class.java)
                    .fabClickState
                    .emit(Unit)
            }
        }
    }
}