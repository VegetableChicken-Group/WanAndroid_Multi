package ui

import android.os.Bundle
import android.widget.ImageView
import coil.load
import com.ndhzs.lib.common.ui.BaseActivity
import com.ndhzs.module.test.R

/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        ui
 * @ClassName:      DebugActivity
 * @Author:         Yan
 * @CreateDate:     2022年06月01日 20:26:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    调试模式下Activity
 */
class DebugActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_debug)
    }
}