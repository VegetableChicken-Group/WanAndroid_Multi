package com.ndhzs.module.main.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.ndhzs.lib.common.config.HOME_PAGE
import com.ndhzs.lib.common.extensions.setOnSingleClickListener
import com.ndhzs.lib.common.service.ServiceManager
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
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }
        binding.vp2Main.adapter = MainVpAdapter(supportFragmentManager, lifecycle) {
            when (it) {
                0 -> ServiceManager.fragment(HOME_PAGE)
                else -> error("state error")
            }
        }
        binding.floatingActionBtn.setOnSingleClickListener {
            lifecycleScope.launch {
                ServiceManager
                    .invoke(IMainService::class)
                    .fabClickState
                    .emit(Unit)
            }
        }
    }
}