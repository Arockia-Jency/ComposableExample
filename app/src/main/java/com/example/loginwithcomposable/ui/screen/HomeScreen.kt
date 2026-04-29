package com.example.loginwithcomposable.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginwithcomposable.utils.PrimaryButton

@Composable


fun HomeScreen(onLogout: () -> Unit) {

    val items = List(5) { "Item #${it + 1}" }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {
            Text(
                text = "Welcome Home 👋",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("List View", fontWeight = FontWeight.SemiBold)
        }

        // 🔹 List (no inner scroll)
        items(items) { item ->
            ListItemUI(item)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Horizontal Grid", fontWeight = FontWeight.SemiBold)
        }

        // 🔹 Horizontal Grid
        item {
            LazyRow {
                items(items) { item ->
                    GridItemUI(item)
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))

            PrimaryButton(
                text = "Logout",
                onClick = { onLogout() },
                enabled = true
            )
        }
    }
}

@Composable
fun ListItemUI(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFE3F2FD))
            .padding(16.dp)
    ) {
        Text(text)
    }
}

@Composable
fun GridItemUI(text: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .width(120.dp)
            .aspectRatio(1f)
            .background(Color(0xFFFFF3E0)),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}