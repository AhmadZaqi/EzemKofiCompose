package com.example.ezemkofi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezemkofi.ui.theme.EzemKofiTheme

@Composable
fun CategoryItem(
    categoryName: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Text(
        text = categoryName,
        color = if (isSelected){
            MaterialTheme.colorScheme.onPrimary
        }
        else MaterialTheme.colorScheme.onTertiary,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
            .background(
                color = if (isSelected){
                    MaterialTheme.colorScheme.primary
                }
                else MaterialTheme.colorScheme.tertiary,
                shape = MaterialTheme.shapes.extraLarge
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

@Preview
@Composable
private fun Preview() {
    EzemKofiTheme {
        CategoryItem(categoryName = "Espresso", isSelected = false)
    }
}