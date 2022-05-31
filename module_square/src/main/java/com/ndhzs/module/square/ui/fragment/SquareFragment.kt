package com.ndhzs.module.square.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.config.SQUARE_SHOW
import com.ndhzs.lib.common.ui.BaseFragment
import com.ndhzs.module.square.R

/**
 * @ClassName SquareFragment
 * @author Silence~ (Zhu Zhaoting)
 * @date 2022/5/31 7:18
 * @Description
 */
@Route(path = SQUARE_SHOW)
class SquareFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.square_fragment_square, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}