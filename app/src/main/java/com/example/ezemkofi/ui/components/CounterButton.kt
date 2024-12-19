package com.example.ezemkofi.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezemkofi.ui.theme.EzemKofiTheme

@Composable
fun CounterButton(
    count: Int,
    onIncrease: ()->Unit,
    onDecrease: ()->Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onTertiary,
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .width(100.dp)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = "-",
                modifier = Modifier.clickable { onDecrease() },
                fontSize = 22.sp
            )
            Text(
                text = count.toString(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "+",
                modifier = Modifier.clickable { onIncrease() },
                fontSize = 22.sp
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    EzemKofiTheme {
        CounterButton(count = 1, onIncrease = { /*TODO*/ }, onDecrease = { /*TODO*/ })
    }
}