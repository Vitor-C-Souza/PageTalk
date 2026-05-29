package com.pagetalk.app.ui.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pagetalk.app.ui.components.AddBookCard
import com.pagetalk.app.ui.components.BookCard
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun ContinueListen(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "Continue ouvindo",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AddBookCard(onClick = onAddClick)
            }
            items(5) { index ->
                BookCard(
                    title = "Livro ${index + 1}",
                    author = "Autor ${index + 1}",
                    progress = 0.25f + (index * 0.10f),
                    coverGradient = if (index % 2 == 0)
                        Brush.linearGradient(listOf(Color(0xFF7C5CFF), Color(0xFFB8A9FF)))
                    else
                        Brush.linearGradient(listOf(Color(0xFF9F8CFF), Color(0xFF7C5CFF)))
                )
            }
        }
    }
}

@Preview
@Composable
private fun ContinueListenPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        ContinueListen()
    }
}
