package com.ndhzs.lib.web.helper

import android.content.Context
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import com.ndhzs.lib.web.R


/**
 *
 * @ProjectName:    WanAndroid_Multi
 * @Package:        com.ndhzs.lib.web.helper
 * @ClassName:      PopupHelper
 * @Author:         Yan
 * @CreateDate:     2022年06月04日 02:55:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    控制菜单选项
 */

fun Context.popup(view : View,@MenuRes menu : Int,clickListener : PopupMenu.OnMenuItemClickListener? = null,dismissListener : PopupMenu.OnDismissListener? = null){
    // 这里的view代表popupMenu需要依附的view
    val popupMenu = PopupMenu(this, view)
    // 获取布局文件
    popupMenu.menuInflater.inflate(menu, popupMenu.menu)
    //显示弹窗
    popupMenu.show()
    popupMenu.setOnMenuItemClickListener(clickListener)
    popupMenu.setOnDismissListener(dismissListener)
}