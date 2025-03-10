import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun ParameterTabs(tabs: List<TabData>) {
    val pagerState = rememberPagerState()
    var selectedParameter by remember { mutableStateOf<Pair<String, List<String>>?>(null) }
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            selectedParameter?.let { parameter ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = parameter.first, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn {
                        items(parameter.second) { content ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Text(text = content, modifier = Modifier.weight(1f))
                            }
                            Divider()
                        }
                    }
                }
            }
        },
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)),
                edgePadding = 8.dp,
                backgroundColor = Color.Red,
                contentColor = Color.White,
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(tab.tabTitle) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalPager(
                count = tabs.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(tabs[pageIndex].subheadings.size) { index ->
                        val subheading = tabs[pageIndex].subheadings[index]
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = subheading.subheadingName,
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedParameter =
                                            subheading.subheadingName to subheading.contents.map { "${it.contentName}: ${it.contentDetails.joinToString()}" }
                                        coroutineScope.launch { bottomSheetState.show() }
                                    }
                                    .padding(8.dp)
                            )
                            Divider()
                        }
                    }
                }
            }
        }
    }
}data class TabData(
    val tabTitle: String,
    val subheadings: List<Subheading>
)

data class Subheading(
    val subheadingName: String,
    val contents: List<Content>
)

data class Content(
    val contentName: String,
    val contentDetails: List<String>
)

@Preview(showBackground = true)
@Composable
fun ParameterTabsPreview() {
    val tabs = listOf(
        TabData(
            tabTitle = "Genel Bilgiler",
            subheadings = listOf(
                Subheading(
                    subheadingName = "Kişisel Bilgiler",
                    contents = listOf(
                        Content(contentName = "Adı", contentDetails = listOf("Gürkan")),
                        Content(contentName = "Soyadı", contentDetails = listOf("Döner"))
                    )
                ),
                Subheading(
                    subheadingName = "İletişim Bilgileri",
                    contents = listOf(
                        Content(
                            contentName = "Telefon Numarası",
                            contentDetails = listOf("05462135454", "03526455455")
                        )
                    )
                )
            )
        ),
        TabData(
            tabTitle = "Adres Bilgileri",
            subheadings = listOf(
                Subheading(
                    subheadingName = "Geçersiz adresler",
                    contents = listOf(
                        Content(
                            contentName = "İş adresi",
                            contentDetails = listOf("evkur halkalı genel merkez")
                        )
                    )
                ),
                Subheading(
                    subheadingName = "Adresler",
                    contents = listOf(
                        Content(
                            contentName = "İş adresi",
                            contentDetails = listOf("evkur halkalı genel merkez")
                        )
                    )
                )
            )
        )
    )
    ParameterTabs(tabs = tabs)
}