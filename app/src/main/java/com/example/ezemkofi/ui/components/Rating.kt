package com.example.ezemkofi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezemkofi.ui.theme.EzemKofiTheme

@Composable
fun Rating(
    color: Color,
    rating: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color, MaterialTheme.shapes.extraLarge)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = rating.toString(),
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun Preview() {
    EzemKofiTheme {
        Rating(
            color = MaterialTheme.colorScheme.secondary,
            rating = 5.0
        )
    }
}