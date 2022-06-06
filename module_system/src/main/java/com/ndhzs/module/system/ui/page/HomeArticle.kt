package com.ndhzs.module.system.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import com.ndhzs.module.system.logic.model.DataX

/**
 *
 * @author :WhiteNight123(Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @date 2022/6/6
 */
@Composable
fun HomeArticleItem(homeArticle: DataX) {

    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = homeArticle.title, style = MaterialTheme.typography.h6)
                Text(
                    text = homeArticle.desc,
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Row(modifier = Modifier.padding(top = 6.dp)) {
                    Text(
                        text = homeArticle.author,
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray
                    )
                    Text(
                        text = homeArticle.niceDate,
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
            Image(
                painter = rememberImagePainter("https://marketplace.canva.cn/64H44/MABr1g64H44/2/tl/canva-google%E5%9B%BE%E6%A0%87-google-icon-MABr1g64H44.png"),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(57.dp)
                    .height(101.dp)
                    .padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun HomeArticleList(viewModel: HomeArticleViewModel) {
    val homeArticles = viewModel.homeArticle.collectAsLazyPagingItems()
    LazyColumn() {
        itemsIndexed(homeArticles) { _, homeArticle ->
            HomeArticleItem(homeArticle!!)

        }
    }
}