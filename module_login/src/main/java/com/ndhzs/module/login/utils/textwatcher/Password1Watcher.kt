package com.ndhzs.module.login.utils.textwatcher

import android.text.Editable
import com.google.android.material.textfield.TextInputLayout

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/30 16:57
 */
class Password1Watcher(
  password: TextInputLayout
) : BaseTextWatcher(password) {
  
  override fun afterTextChanged(s: Editable) {
    super.afterTextChanged(s)
  }
}