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
import com.pagetalk.app.ui.components.RecentDocumentItem
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun RecentDocuments(
    modifier: Modifier = Modifier
) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(3) { index ->
                RecentDocumentItem(
                    title = "Documento ${index + 1}",
                    author = "Autor ${index + 1}",
                    progress = if (index == 0) 0.6f else if (index == 1) 0.4f else 0.25f
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecentDocumentsPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        RecentDocuments()
    }
}
