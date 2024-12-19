package com.example.ezemkofi.ui.components

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezemkofi.R
import com.example.ezemkofi.ui.theme.EzemKofiTheme

@SuppressLint("DefaultLocale")
@Composable
fun CoffeeByCategoryItem(
    coffeeName: String,
    rating: Double,
    price: Double,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.width(160.dp)){
        Box(
            modifier
                .align(Alignment.BottomCenter)
                .height(170.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.large)
        )
        Column(
            modifier = Modifier.align(Alignment.TopCenter).padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(120.dp)
            )
            Text(
                text = coffeeName,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Rating(
                color = MaterialTheme.colorScheme.secondary,
                rating = rating,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$" + String.format("%.2f", price),
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    EzemKofiTheme {
        CoffeeByCategoryItem(coffeeName = "Coffee", rating = 5.00, price = 3.45, image = R.drawable.latte_luxe)
    }
}