package com.ndhzs.module.system.page.viewmodel

import com.ndhzs.lib.common.ui.mvvm.BaseViewModel
import com.ndhzs.module.system.bean.SystemData
import com.ndhzs.module.system.network.SystemService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @author 1446157077 (Watermelon02)
 * @email 1446157077@qq.com
 * @date 2022/5/29 18:11
 */
class SystemViewModel : BaseViewModel() {
    var system: SystemData? = null
    fun querySystem(successAction: (SystemData) -> Unit) {
        if (system == null) {
            synchronized(this) {
                if (system == null) {
                    SystemService.INSTANCE.querySystem().subscribeOn(Schedulers.io())  // 线程切换
                        .observeOn(AndroidSchedulers.mainThread())
                        .safeSubscribe(object : SingleObserver<SystemData> {
                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onError(e: Throwable) {
                            }

                            override fun onSuccess(t: SystemData) {
                                successAction(t)
                                system = t
                            }
                        })
                } else successAction(system!!)
            }
        } else return successAction(system!!)
    }
}