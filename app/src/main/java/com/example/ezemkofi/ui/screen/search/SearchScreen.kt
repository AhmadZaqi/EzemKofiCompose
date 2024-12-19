package com.example.ezemkofi.ui.screen.search

import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ezemkofi.di.Injection
import com.example.ezemkofi.model.Coffee
import com.example.ezemkofi.model.FakeCoffeeDataSource
import com.example.ezemkofi.ui.ViewModelFactory
import com.example.ezemkofi.ui.common.UiState
import com.example.ezemkofi.ui.components.CoffeeItem
import com.example.ezemkofi.ui.theme.EzemKofiTheme

@Composable
fun SearchScreen(
    onBack: ()-> Unit,
    navigateToDetail: (Int)-> Unit,
    viewModel: SearchViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    modifier: Modifier = Modifier
) {
    val uiStateCoffee by viewModel.uiStateCoffee.collectAsState(initial = UiState.Loading)
    val context = LocalContext.current

    var query by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = query) {
        viewModel.searchCoffee(query)
    }
    uiStateCoffee.let { uiState ->
        when (uiState){
            is UiState.Loading->{
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is UiState.Success->{
                SearchContent(
                    onBack = onBack,
                    modifier = modifier,
                    query = query,
                    onQueryChange = {query = it},
                    listCoffee = uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error->{
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun SearchContent(
    query: String,
    onQueryChange: (String) -> Unit,
    onBack: ()-> Unit,
    listCoffee: List<Coffee>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "Back",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onBack()
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))
            MySearchBar(
                query = query,
                onQueryChange = onQueryChange,
                modifier = modifier
            )
        }
        HorizontalDivider()
        LazyColumn(
            Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(listCoffee, key = {it.id}){ coffee->
                CoffeeItem(
                    coffeeName = coffee.name,
                    rating = coffee.rating,
                    price = coffee.price,
                    image = coffee.image,
                    category = coffee.category.name,
                    modifier = Modifier
                        .animateItem(fadeInSpec = tween(250))
                        .clickable {
                            navigateToDetail(coffee.id)
                        }
                )
            }
        }
    }
}

@Composable
fun MySearchBar(
    query: String,
    onQueryChange: (String)-> Unit,
    modifier: Modifier = Modifier
) {
//    SearchBar(
//        query = query,
//        onQueryChange = onQueryChange,
//        onSearch = {},
//        active = false,
//        onActiveChange = {},
//        leadingIcon = {
//            Icon(imageVector = Icons.Default.Search, contentDescription = null)
//        },
//        placeholder = {
//            Text(text = "Find your perfect coffee")
//        },
//        modifier = modifier.fillMaxWidth().padding(16.dp)
//    ) {
//
//    }
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Find your perfect coffee")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        shape = MaterialTheme.shapes.extraLarge,
        singleLine = true,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    EzemKofiTheme {
        SearchScreen(onBack = { /*TODO*/ }, {})
    }
}