package com.ndhzs.module.system

import android.os.Bundle
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindActivity
import com.ndhzs.module.system.databinding.ActivitySystemBinding

class SystemActivity : BaseVmBindActivity<SystemViewModel, ActivitySystemBinding>() {

    override fun onSetContentViewBefore() {
        super.onSetContentViewBefore()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}