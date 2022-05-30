package com.ndhzs.module.login.utils.textwatcher

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2022/5/30 16:56
 */
open class BaseTextWatcher(
  textInputLayout: TextInputLayout,
) : TextWatcher {
  
  protected val mLayout = textInputLayout
  
  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
  }
  
  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
  }
  
  override fun afterTextChanged(s: Editable) {
    if (s.length > mLayout.counterMaxLength) {
      mLayout.error = "超出限定字数！"
    }else {
      mLayout.error = null
    }
  }
}