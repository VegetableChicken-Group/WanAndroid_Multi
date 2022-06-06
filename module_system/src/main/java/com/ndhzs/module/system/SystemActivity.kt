package com.ndhzs.module.system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.ndhzs.lib.common.config.SYSTEM_ENTRY
import com.ndhzs.module.system.ui.page.HomeArticleList
import com.ndhzs.module.system.ui.page.HomeArticleViewModel
import com.ndhzs.module.system.ui.theme.WanAndroid_MultiTheme

@Route(path = SYSTEM_ENTRY)
class SystemActivity : ComponentActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(HomeArticleViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            WanAndroid_MultiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeArticleList(viewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WanAndroid_MultiTheme {

    }
}