package com.pagetalk.app.ui.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.ui.components.RecentDocumentItem
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun RecentDocuments(
    books: List<Book>,
    onBookClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    if (books.isEmpty()) return

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Recentes",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            books.forEach { book ->
                RecentDocumentItem(
                    title = book.title,
                    author = book.author,
                    progress = book.progress,
                    onClick = { onBookClick(book.id) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecentDocumentsPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        RecentDocuments(
            books = listOf(
                Book(
                    id = 1,
                    title = "Documento 1",
                    author = "Autor 1",
                    progress = 0.6f,
                    uri = "",
                    size = "1MB",
                    pages = 50
                ),
                Book(
                    id = 2,
                    title = "Documento 2",
                    author = "Autor 2",
                    progress = 0.1f,
                    uri = "",
                    size = "5MB",
                    pages = 200
                )
            ),
            onBookClick = {}
        )
    }
}
