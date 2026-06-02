package com.pagetalk.app.ui.presentation.importpdf

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.ui.components.HeaderCustom
import com.pagetalk.app.ui.presentation.importpdf.components.RecentUploadItem
import com.pagetalk.app.ui.presentation.importpdf.components.UploadArea
import com.pagetalk.app.ui.theme.PageTalkTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportPdfScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
    viewModel: ImportPdfViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val recentBooks by viewModel.recentBooks.collectAsState()

    val pdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            uri?.let {
                viewModel.importPdf(context, it)
            }
        }
    )

    ImportPdfContent(
        modifier = modifier,
        recentBooks = recentBooks,
        onNavigateBack = onNavigateBack,
        onUploadClick = {
            pdfLauncher.launch(arrayOf("application/pdf"))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportPdfContent(
    recentBooks: List<Book>,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
    onUploadClick: () -> Unit = {}
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
                UploadArea(onClick = onUploadClick)
            }

            // Recently Uploaded section
            if (recentBooks.isNotEmpty()) {
                item {
                    Text(
                        text = "Enviados recentemente",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                items(recentBooks) { book ->
                    RecentUploadItem(
                        name = book.title,
                        details = "${book.size} • ${book.pages} páginas",
                        status = if (book.progress >= 1f) UploadStatus.Complete else UploadStatus.InProgress(
                            book.progress
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ImportPdfPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        ImportPdfContent(
            recentBooks = listOf(
                Book(
                    title = "documento-1.pdf",
                    author = "Desconhecido",
                    uri = "",
                    size = "2.4 MB",
                    pages = 24,
                    progress = 1f
                ),
                Book(
                    title = "documento-2.pdf",
                    author = "Desconhecido",
                    uri = "",
                    size = "1.8 MB",
                    pages = 18,
                    progress = 0.4f
                )
            )
        )
    }
}
