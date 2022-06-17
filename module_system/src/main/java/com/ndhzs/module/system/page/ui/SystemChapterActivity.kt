package com.ndhzs.module.system.page.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.ndhzs.lib.common.config.SYSTEM_SYSTEM_ARTICLE
import com.ndhzs.lib.common.ui.mvvm.BaseVmBindActivity
import com.ndhzs.module.system.adapter.SystemChapterAdapter
import com.ndhzs.module.system.databinding.ActivitySystemArticleBinding
import com.ndhzs.module.system.page.viewmodel.SystemArticleViewModel

@Route(path = SYSTEM_SYSTEM_ARTICLE)
class SystemChapterActivity :
    BaseVmBindActivity<SystemArticleViewModel, ActivitySystemArticleBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chapterName = intent.getStringArrayListExtra("chapterName")
        val chapterId  = intent.getIntegerArrayListExtra("chapterId")
        binding.systemChapterVp.adapter = SystemChapterAdapter(chapterId!!, this)
        TabLayoutMediator(binding.systemChapterTab, binding.systemChapterVp) { tab, position ->
            tab.text = chapterName!![position]
        }.attach()
    }
}