import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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
import kotlin.collections.component1
import kotlin.collections.component2


@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun ParameterTabs(parameters: List<List<Pair<String, List<String>>>>) {
    val pagerState = rememberPagerState()
    var selectedParameter by remember { mutableStateOf<Pair<String, List<String>>?>(null) }
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)

    var favoriteItemList by remember { mutableStateOf(loadFavorites(sharedPreferences)) }
    var checkboxStates by remember { mutableStateOf(mutableMapOf<Pair<String, String>, Boolean>()) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            selectedParameter?.let { parameter ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = parameter.first, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn {
                        items(parameter.second) { content ->
                            val key = parameter.first to content
                            val isChecked = checkboxStates[key] ?: false

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)) {
                                Text(text = content, modifier = Modifier.weight(1f))
                                Checkbox(
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color.Red,
                                        uncheckedColor = Color.Gray
                                    ),
                                    checked = isChecked,
                                    onCheckedChange = { checked ->
                                        checkboxStates = checkboxStates.toMutableMap()
                                            .apply { put(key, checked) }
                                        val currentFavorites =
                                            favoriteItemList[parameter.first]?.toMutableList()
                                                ?: mutableListOf()
                                        if (checked) {
                                            currentFavorites.add(content)
                                            saveFavorite(
                                                sharedPreferences,
                                                parameter.first,
                                                content
                                            )
                                        } else {
                                            currentFavorites.remove(content)
                                            removeFavorite(
                                                sharedPreferences,
                                                parameter.first,
                                                content
                                            )
                                        }
                                        favoriteItemList = favoriteItemList.toMutableMap().apply {
                                            if (currentFavorites.isEmpty()) remove(parameter.first) else put(
                                                parameter.first,
                                                currentFavorites
                                            )
                                        }
                                    },
                                    modifier = Modifier.padding(8.dp)
                                )
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
                parameters.forEachIndexed { index, _ ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text("Tab ${index + 1}") }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalPager(
                count = parameters.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)) {
                    items(parameters[pageIndex].size) { index ->
                        val (title, contentList) = parameters[pageIndex][index]
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedParameter = title to contentList
                                        coroutineScope.launch { bottomSheetState.show() }
                                    }
                                    .padding(8.dp)
                            )
                            Divider()
                            favoriteItemList[title]?.let { favorites ->
                                Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp)) {
                                    favorites.forEach { favorite ->
                                        Text(
                                            text = favorite,
                                            style = MaterialTheme.typography.body1,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun loadFavorites(sharedPreferences: SharedPreferences): MutableMap<String, List<String>> {
    val favorites = mutableMapOf<String, List<String>>()
    sharedPreferences.all.forEach { (key, value) ->
        if (value is Set<*>) {
            favorites[key] = value.filterIsInstance<String>()
        }
    }
    return favorites
}

fun saveFavorite(sharedPreferences: SharedPreferences, parameter: String, content: String) {
    val favorites =
        sharedPreferences.getStringSet(parameter, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
    favorites.add(content)
    sharedPreferences.edit().putStringSet(parameter, favorites).apply()
}

fun removeFavorite(sharedPreferences: SharedPreferences, parameter: String, content: String) {
    val favorites =
        sharedPreferences.getStringSet(parameter, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
    favorites.remove(content)
    if (favorites.isEmpty()) {
        sharedPreferences.edit().remove(parameter).apply()
    } else {
        sharedPreferences.edit().putStringSet(parameter, favorites).apply()
    }
}

@Preview(showBackground = true)
@Composable
fun ParameterTabsPreview() {
    val parameters = List(6) { tabIndex ->
        List(5) { paramIndex ->
            "Parameter ${paramIndex + 1}" to List(30) { "Content ${paramIndex + 1} - Detail ${it + 1}" }
        }
    }
    ParameterTabs(parameters = parameters)
}
