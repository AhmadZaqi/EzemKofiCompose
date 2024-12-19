package com.example.ezemkofi.ui.screen.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezemkofi.ui.theme.EzemKofiTheme
import com.example.ezemkofi.R
import com.example.ezemkofi.di.Injection
import com.example.ezemkofi.model.Coffee
import com.example.ezemkofi.model.CoffeeCategory
import com.example.ezemkofi.ui.ViewModelFactory
import com.example.ezemkofi.ui.common.UiState
import com.example.ezemkofi.ui.components.CategoryItem
import com.example.ezemkofi.ui.components.CoffeeByCategoryItem
import com.example.ezemkofi.ui.components.CoffeeItem

@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToProfile: ()-> Unit,
    navigateToDetail: (Int)-> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    modifier: Modifier = Modifier
) {
    val uiStateTopPicks by viewModel.uiStateTopPicks.collectAsState(initial = UiState.Loading)
    val uiStateCategory by viewModel.uiStateCategory.collectAsState(initial = UiState.Loading)
    val uiStateCoffeeByCategory by viewModel.uiStateCoffee.collectAsState(initial = UiState.Loading)
    val context = LocalContext.current

    var selectedCategory by remember {
        mutableIntStateOf(1)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getTopPicksCoffee()
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getCoffeeCategory()
    }
    LaunchedEffect(key1 = selectedCategory) {
        viewModel.getCoffeeByCategory(selectedCategory)
    }

    uiStateCategory.let { stateCategory ->
        when (stateCategory){
            is UiState.Loading->{
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is UiState.Success->{
                uiStateCoffeeByCategory.let { stateCoffeeByCategory ->
                    when (stateCoffeeByCategory){
                        is UiState.Loading->{
                            Box(modifier = Modifier.fillMaxSize()){
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        }
                        is UiState.Success->{
                            uiStateTopPicks.let { stateTopPicksCoffee ->
                                when (stateTopPicksCoffee){
                                    is UiState.Loading->{
                                        Box(modifier = Modifier.fillMaxSize()){
                                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                        }
                                    }
                                    is UiState.Success->{
                                        HomeContent(
                                            navigateToSearch = navigateToSearch,
                                            navigateToCart = navigateToCart,
                                            navigateToProfile = navigateToProfile,
                                            modifier = modifier,
                                            category = stateCategory.data,
                                            topPicks = stateTopPicksCoffee.data,
                                            coffeeByCategory = stateCoffeeByCategory.data,
                                            selectedCategory = selectedCategory,
                                            onSelectedCategory = {
                                                selectedCategory = it
                                            },
                                            navigateToDetail = navigateToDetail
                                        )
                                    }
                                    is UiState.Error->{
                                        Toast.makeText(context, stateTopPicksCoffee.errorMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                        is UiState.Error->{
                            Toast.makeText(context, stateCoffeeByCategory.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            is UiState.Error->{
                Toast.makeText(context, stateCategory.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun HomeContent(
    navigateToSearch: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToProfile: ()-> Unit,
    category: List<CoffeeCategory>,
    topPicks: List<Coffee>,
    coffeeByCategory: List<Coffee>,
    selectedCategory: Int,
    onSelectedCategory: (Int) -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .width(140.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navigateToSearch()
                        }
                )
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navigateToCart()
                        }
                )
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navigateToProfile()
                        }
                )
            }
        }
        Text(
            text = "Categories",
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(category, key = {it.id}){ category ->
                CategoryItem(
                    categoryName = category.name,
                    isSelected = selectedCategory == category.id,
                    modifier = Modifier
                        .clickable {
                            onSelectedCategory(category.id)
                        }
                )
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(coffeeByCategory, key = {it.id}){ item ->
                CoffeeByCategoryItem(
                    coffeeName = item.name,
                    rating = item.rating,
                    price = item.price,
                    image = item.image,
                    modifier = Modifier
                        .clickable {
                        navigateToDetail(item.id)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Top Picks",
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp)
        ){
            topPicks.forEach { coffee ->
                CoffeeItem(
                    coffeeName = coffee.name,
                    rating = coffee.rating,
                    price = coffee.price,
                    image = coffee.image,
                    category = coffee.category.name,
                    modifier = Modifier.clickable {
                        navigateToDetail(coffee.id)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    EzemKofiTheme {
        HomeScreen({},{},{}, {})
    }
}