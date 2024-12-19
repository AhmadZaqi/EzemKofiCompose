package com.example.ezemkofi.ui.screen.cart

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ezemkofi.di.Injection
import com.example.ezemkofi.model.Cart
import com.example.ezemkofi.ui.ViewModelFactory
import com.example.ezemkofi.ui.common.UiState
import com.example.ezemkofi.ui.components.CoffeeItem
import com.example.ezemkofi.ui.components.CounterButton
import com.example.ezemkofi.ui.components.DialogBox
import com.example.ezemkofi.ui.components.RoundedButton
import com.example.ezemkofi.ui.theme.EzemKofiTheme
import java.util.Locale

@Composable
fun CartScreen(
    onBack: () -> Unit,
    viewModel: CartViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    modifier: Modifier = Modifier
) {
    val uiStateCart by viewModel.uiStateCart.collectAsState()
    val context = LocalContext.current

    when (val state = uiStateCart) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
        }
        is UiState.Success -> {
            CartContent(
                listCart = state.data,
                onUpdateQty = { coffeeId, newQty ->
                    viewModel.updateCoffeeInCart(coffeeId, newQty)
                },
                onBack = onBack,
                modifier = modifier,
                onOrderClick = {
                    viewModel.orderAllCoffeeInCart()
                }
            )
        }
        is UiState.Error -> {
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun CartContent(
    onBack: () -> Unit,
    listCart: List<Cart>,
    onUpdateQty: (coffeeId: Int, newQty: Int)-> Unit,
    onOrderClick: ()-> Unit,
    modifier: Modifier = Modifier,
) {
    val totalPayment = listCart.sumOf {
        it.coffee.price * it.count
    }
    val messageDialog = "Total Payment : $" + String.format("%.2f", totalPayment) + "\nAre you sure?"
    var showDialog by remember {
        mutableStateOf(false)
    }

    if (showDialog){
        DialogBox(
            onDismissRequest = { showDialog = false },
            onConfirmation = {
                onOrderClick()
                showDialog = false
            },
            title = "Confirmation",
            message = messageDialog
        )
    }
    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "Back",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.TopStart)
                    .clickable {
                        onBack()
                    }
            )
            Text(
                text = "Your Cart",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        HorizontalDivider()
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listCart, key = {it.coffee.id}){ item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CoffeeItem(
                        coffeeName = item.coffee.name,
                        rating = item.coffee.rating,
                        price = item.coffee.price * item.count,
                        image = item.coffee.image,
                        category = item.coffee.category.name,
                        modifier = Modifier
                            .weight(1f)
                    )
                    CounterButton(
                        count = item.count,
                        onIncrease = {
                            onUpdateQty(item.coffee.id, item.count + 1)
                        },
                        onDecrease = {
                            onUpdateQty(item.coffee.id, item.count - 1)
                        })
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Total : $" + String.format("%.2f", totalPayment),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        RoundedButton(
            text = "Checkout",
            onClick = { showDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
            enabled = listCart.isNotEmpty()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    EzemKofiTheme {
        CartScreen({})
    }
}