package com.dicoding.ezkart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    NavHost(navController, startDestination = "list_page") {
        composable("list_page") {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                TopBar(navController)
                Spacer(modifier = Modifier.height(8.dp))
                ListScreen(navController)
            }
        }

        composable(
            route = "detail_page/{item}",
            arguments = listOf(navArgument("item") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("item")?.let { item ->
                DetailScreen(item)
            }
        }

        composable("about_page") {
            About(navController)
        }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        TopBar(navController)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Search(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .fillMaxWidth()
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

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite_page",
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
fun ListScreen(navController: NavController) {
    val itemList = listMenu

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(itemList) { menu ->
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

@Composable
fun DetailScreen(item: String) {
    Text(
        text = "Detail for $item",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    EzkartTheme {
        EzkrtApp()
    }
}
