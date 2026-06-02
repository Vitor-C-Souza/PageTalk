package com.pagetalk.app.di

import androidx.room.Room
import com.pagetalk.app.data.local.AppDatabase
import com.pagetalk.app.data.repository.BookRepositoryImpl
import com.pagetalk.app.domain.repository.BookRepository
import com.pagetalk.app.domain.usecase.AddBookUseCase
import com.pagetalk.app.domain.usecase.GetAllBooksUseCase
import com.pagetalk.app.domain.usecase.UpdateBookUseCase
import com.pagetalk.app.ui.presentation.importpdf.ImportPdfViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "pagetalk_db",
        ).build()
    }

    single { get<AppDatabase>().bookDao() }

    // Bind interface to implementation
    single<BookRepository> { BookRepositoryImpl(get()) }

    // Use Cases
    single { GetAllBooksUseCase(get()) }
    single { AddBookUseCase(get()) }
    single { UpdateBookUseCase(get()) }

    viewModel { ImportPdfViewModel(get(), get(), get()) }
}
