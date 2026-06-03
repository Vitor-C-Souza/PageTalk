package com.pagetalk.app.ui.presentation.importpdf

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.domain.usecase.AddBookUseCase
import com.pagetalk.app.domain.usecase.GetAllBooksUseCase
import com.pagetalk.app.domain.usecase.UpdateBookUseCase
import com.tom_roush.pdfbox.pdmodel.PDDocument
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class ImportPdfViewModel(
    getAllBooksUseCase: GetAllBooksUseCase,
    private val addBookUseCase: AddBookUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
) : ViewModel() {

    // Queue for processing files one by one (Sequential)
    private val importChannel = Channel<ImportTask>(Channel.UNLIMITED)

    // Only show books from the last 24h as "recent" in this screen
    val recentBooks: StateFlow<List<Book>> = getAllBooksUseCase()
        .map { books ->
            books.filter { (System.currentTimeMillis() - it.timestamp) < (24 * 60 * 60 * 1000) }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        // Start the consumer that processes tasks from the queue one by one
        viewModelScope.launch {
            importChannel.receiveAsFlow().collect { task ->
                processFile(task.context, task.uri, task.book)
            }
        }
    }

    fun importPdf(context: Context, uri: Uri) {
        viewModelScope.launch {
            // 1. Get initial metadata and insert as "Queued" (Visual feedback)
            val initialBook = getInitialMetadata(context, uri)
            val generatedId = addBookUseCase(initialBook)

            // 2. Add to queue for sequential processing with the real ID
            importChannel.send(ImportTask(context, uri, initialBook.copy(id = generatedId)))
        }
    }

    private suspend fun processFile(context: Context, uri: Uri, book: Book) {
        withContext(Dispatchers.IO) {
            try {
                // Update to In Progress (10%)
                var currentBook = book.copy(progress = 0.1f)
                updateBookUseCase(currentBook)

                // Actual PDF Processing: Count pages using PDFBox
                val pageCount = getPdfPageCount(context, uri)

                // Update with page info (60%)
                currentBook = currentBook.copy(progress = 0.6f, pages = pageCount)
                updateBookUseCase(currentBook)

                // On powerful devices like S24+, this is too fast. 
                // We add a tiny delay to ensure the user sees the transition states.
                delay(600)

                // Mark as complete
                currentBook = currentBook.copy(progress = 1.0f)
                updateBookUseCase(currentBook)
            } catch (e: Exception) {
                // Error handling
            }
        }
    }

    private fun getPdfPageCount(context: Context, uri: Uri): Int {
        return try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                PDDocument.load(inputStream).use { document ->
                    document.numberOfPages
                }
            } ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun getInitialMetadata(context: Context, uri: Uri): Book {
        var name = "Documento"
        var size = "0 MB"

        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            if (cursor.moveToFirst()) {
                name = cursor.getString(nameIndex)
                val sizeBytes = cursor.getLong(sizeIndex)
                size = String.format(Locale.US, "%.1f MB", sizeBytes / (1024f * 1024f))
            }
        }

        return Book(
            title = name,
            author = "Pendente",
            uri = uri.toString(),
            size = size,
            pages = 0,
            progress = 0.01f // Tiny progress to show the spinner immediately
        )
    }

    private data class ImportTask(
        val context: Context,
        val uri: Uri,
        val book: Book
    )
}
