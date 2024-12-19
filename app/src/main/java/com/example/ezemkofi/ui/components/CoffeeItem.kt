package com.example.ezemkofi.ui.components

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezemkofi.R
import com.example.ezemkofi.model.Coffee
import com.example.ezemkofi.ui.theme.EzemKofiTheme

@SuppressLint("DefaultLocale")
@Composable
fun CoffeeItem(
    coffeeName: String,
    rating: Double,
    price: Double,
    @DrawableRes image: Int,
    category: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box{
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Rating(
                color = MaterialTheme.colorScheme.primary,
                rating = rating,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.padding(top = 8.dp, end = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = coffeeName,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = category,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onTertiary,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$" + String.format("%.2f", price),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    EzemKofiTheme {
        CoffeeItem(
            coffeeName = "Coffee",
            rating = 5.0,
            price = 3.45,
            image = R.drawable.americano_awakening,
            category = "Latte"
        )
    }
}