package com.west.cstack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.west.cstack.ui.theme.CStackTheme

class MainActivity : ComponentActivity() {

    /*external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("cstack")
        }
    }*/

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            CStackTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("CStack App") },
                            actions = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("Dark")
                                    Switch(
                                        checked = isDarkTheme,
                                        onCheckedChange = { isDarkTheme = it }
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            Text(
                                text = "© 2025 CStack",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                ) { padding ->
                    Surface(modifier = Modifier.padding(padding)) {
                        //val message = remember { stringFromJNI() }
                        Column(modifier = Modifier.fillMaxSize()) {
                            /*Text(
                                text = message,
                                modifier = Modifier.padding(16.dp)
                            )*/
                            MyApp()
                        }
                    }
                }
            }
        }
    }

    class MyViewModel : ViewModel() {
        private val _products = MutableLiveData<List<String>>(
            listOf("SmartPhone", "Laptop", "Computer")
        )
        val products: LiveData<List<String>> = _products

        fun addProducts(product: String) {
            _products.value = _products.value?.plus(product)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ProductListScreen(navController: NavHostController, viewModel: MyViewModel) {
        val products by viewModel.products.observeAsState(emptyList())

        Column(modifier = Modifier.padding(16.dp)) {
            LazyColumn {
                items(products) { product ->
                    Card(
                        onClick = { navController.navigate("details/$product") },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = product,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("add") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddProductScreen(navController: NavHostController, viewModel: MyViewModel) {
        Column(modifier = Modifier.padding(16.dp)) {
            var productName by remember { mutableStateOf("") }
            val rainbowColors = listOf(
                Color.Red, Color(0xFFFFA500), Color.Yellow, Color.Green,
                Color.Blue, Color(0xFF4B0082), Color(0xFF8B00FF)
            )
            val brush = remember { Brush.linearGradient(colors = rainbowColors) }

            TextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Add Products") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(brush = brush)
            )

            Button(
                onClick = {
                    if (productName.isNotBlank()) {
                        viewModel.addProducts(productName)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.padding(top = 30.dp).fillMaxWidth()
            ) {
                Text("Add")
            }
        }
    }

    @Composable
    fun DetailsScreen(productName: String?) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = productName ?: "Unknown Projects",
                style = MaterialTheme.typography.displaySmall
            )
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        val viewModel: MyViewModel = viewModel()

        NavHost(
            navController = navController,
            startDestination = "List",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("List") {
                ProductListScreen(navController, viewModel)
            }
            composable("Add") {
                AddProductScreen(navController, viewModel)
            }
            composable("details/{productName}") { backStackEntry ->
                DetailsScreen(backStackEntry.arguments?.getString("productName"))
            }
        }
    }
}
