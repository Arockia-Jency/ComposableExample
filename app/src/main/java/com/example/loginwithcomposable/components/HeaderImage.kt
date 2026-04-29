package com.example.loginwithcomposable.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loginwithcomposable.R

@Composable
fun HeaderImage(
    imageRes: Int,
    height: Dp = 400.dp
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        contentScale = ContentScale.Crop
    )
}
@Preview
@Composable
fun HeaderImagePreview() {
    HeaderImage(
        imageRes = R.drawable.hearder_image,
        height = 450.dp
    )
}