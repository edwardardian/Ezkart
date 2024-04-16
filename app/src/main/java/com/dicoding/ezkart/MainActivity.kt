package com.dicoding.ezkart

import SearchViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.ezkart.model.listMenu
import com.dicoding.ezkart.ui.components.Search
import com.dicoding.ezkart.ui.theme.EzkartTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzkartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EzkrtApp()
                }
            }
        }
    }
}

@Composable
fun EzkrtApp() {
    val navController = rememberNavController()

    var searchQuery by remember { mutableStateOf("") }

    NavHost(navController, startDestination = "list_page") {
        composable("list_page") {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                TopBar(navController, searchQuery) { query ->
                    searchQuery = query
                }
                Spacer(modifier = Modifier.height(8.dp))
                ListScreen(navController, viewModel(), searchQuery)
            }
        }

        composable(
            route = "detail_page/{item}",
            arguments = listOf(navArgument("item") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("item")?.let { item ->
                val descriptionResId = when (item) {
                    "iPhone 15 Pro" -> R.drawable.img_iphone_15_pro
                    "iPhone 15" -> R.drawable.img_iphone_15
                    "iPhone 14 Pro" -> R.drawable.img_iphone_14_pro
                    "Samsung Galaxy Z Fold 5" -> R.drawable.img_samsung_z_fold_5
                    "Samsung Galaxy Z Flip 5" -> R.drawable.img_samsung_z_flip_5
                    "Samsung Galaxy S23 Ultra" -> R.drawable.img_samsung_s23_ultra
                    "Xiaomi 12 Pro" -> R.drawable.img_xiaomi_12_pro
                    "Poco F5" -> R.drawable.img_poco_f5
                    "Realme GT2 Pro" -> R.drawable.img_realme_gt2_pro
                    "Realme 11 Pro+ 5G" -> R.drawable.img_realme_11_proplus_5g
                    else -> R.drawable.img_poco_f5
                }

                val description = LocalContext.current.resources.getStringArray(R.array.item_description)

                DetailScreen(item, descriptionResId, description, navController)
            }
        }

        composable("about_page") {
            About(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, searchQuery: String, onSearchQueryChanged: (String) -> Unit) {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Search(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .fillMaxWidth(),
                onSearchQueryChanged = onSearchQueryChanged,
                searchQuery = searchQuery
            )
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate("about_page")
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "about_page",
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun ListScreen(navController: NavController, searchViewModel: SearchViewModel = viewModel(), searchQuery: String) {
    val itemList = listMenu
    val searchData by searchViewModel.performSearch(itemList, searchQuery).collectAsState(initial = emptyList())

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(searchData) { menu ->
            com.dicoding.ezkart.ui.components.ListItem(
                menu = menu,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("detail_page/${menu.title}")
                    }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(item: String, imageResourceId: Int, description: Array<String>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Detail",
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to List"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
        ) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1.5f)
                    .align(Alignment.Center)
                    .padding(8.dp)
            )
        }

        Text(
            text = item,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.headlineLarge
        )


        val index = listMenu.indexOfFirst { it.title == item }
        if (index != -1) {
            Text(text = description[index], style = MaterialTheme.typography.bodyLarge)
        } else {
            Text(text = "Deskripsi tidak ditemukan untuk $item", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    EzkartTheme {
        EzkrtApp()
    }
}
