package com.example.ezemkofi.ui.screen.detail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ezemkofi.R
import com.example.ezemkofi.di.Injection
import com.example.ezemkofi.ui.ViewModelFactory
import com.example.ezemkofi.ui.common.UiState
import com.example.ezemkofi.ui.components.CounterButton
import com.example.ezemkofi.ui.components.RoundedButton
import com.example.ezemkofi.ui.theme.EzemKofiTheme

@Composable
fun DetailScreen(
    coffeeId: Int,
    onBack: () -> Unit,
    navigateToCart: ()-> Unit,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    modifier: Modifier = Modifier
) {
    val uiStateCoffeeDetail by viewModel.uiStateCoffeeDetail.collectAsState(initial = UiState.Loading)
    val context = LocalContext.current

    uiStateCoffeeDetail.let { state ->
        when (state){
            is UiState.Loading->{
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                viewModel.getCoffeeDetail(coffeeId)
            }
            is UiState.Success->{
                val data = state.data
                DetailContent(
                    coffeeName = data.name,
                    description = data.description,
                    image = data.image,
                    price = data.price,
                    onBack = onBack,
                    onAddToCart = {
                        viewModel.addCoffeeToCart(coffeeId, it)
                        navigateToCart()
                    },
                    modifier = modifier
                )
            }
            is UiState.Error->{
                Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun DetailContent(
    coffeeName: String,
    description: String,
    @DrawableRes image: Int,
    price: Double,
    onBack: () -> Unit,
    onAddToCart: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var qty by remember {
        mutableIntStateOf(1)
    }
    Box(
        modifier = modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp)
                )
        )
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(30.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                )
                .clickable { onBack() },
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = "Details",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        )
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 0.dp, y = (-112).dp)
                .fillMaxWidth(0.75f),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {

            Text(
                text = coffeeName,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )
            Text(
                text = description,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$" + String.format("%.2f", price),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp
                )
                CounterButton(
                    count = qty,
                    onIncrease = { if (qty < 100) qty++ },
                    onDecrease = { if (qty > 1) qty-- }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            RoundedButton(
                text = "+ Add To Cart",
                onClick = {onAddToCart(qty)},
                modifier = Modifier.fillMaxWidth(),
                enabled = qty != 0
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    EzemKofiTheme {
        DetailContent(
            coffeeName = "Coffee",
            description = "Enakkkkkkkkkkk",
            image = R.drawable.latte_luxe,
            price = 2.00,
            onBack = { /*TODO*/ },
            onAddToCart = { /*TODO*/ })
    }
}