package com.pagetalk.app.ui.presentation.importpdf

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pagetalk.app.ui.components.HeaderCustom
import com.pagetalk.app.ui.presentation.importpdf.components.RecentUploadItem
import com.pagetalk.app.ui.presentation.importpdf.components.UploadArea
import com.pagetalk.app.ui.theme.PageTalkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportPdfScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colorScheme.background,
        topBar = {
            HeaderCustom(
                title = "Importar PDF",
                subtitle = "Adicione documentos à sua biblioteca",
                onBackClick = onNavigateBack
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Upload Area
            item {
                UploadArea(onClick = { /* TODO: Open file picker */ })
            }

            // Recently Uploaded section
            item {
                Column {
                    Text(
                        text = "Enviados recentemente",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        RecentUploadItem(
                            name = "documento-1.pdf",
                            details = "2.4 MB • 24 páginas",
                            status = UploadStatus.Complete
                        )
                        RecentUploadItem(
                            name = "documento-2.pdf",
                            details = "1.8 MB • 18 páginas",
                            status = UploadStatus.InProgress(0.75f)
                        )
                        RecentUploadItem(
                            name = "documento-3.pdf",
                            details = "3.2 MB • 32 páginas",
                            status = UploadStatus.Queued
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ImportPdfPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        ImportPdfScreen()
    }
}
