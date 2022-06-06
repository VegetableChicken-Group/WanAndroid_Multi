package debug

import android.content.Context
import androidx.annotation.CallSuper
import com.ndhzs.lib.common.BaseApp

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        debug
 * @ClassName:      DebugAPP
 * @Author:         Yan
 * @CreateDate:     2022年06月01日 20:27:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    调试模式下App
 */
class DebugAPP : BaseApp(){
    companion object {
        lateinit var appContextDebug: Context
            private set
    }

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        appContextDebug = this
    }
}