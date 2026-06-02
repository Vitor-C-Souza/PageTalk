package com.pagetalk.app.data.local

import com.pagetalk.app.domain.model.Book

fun BookEntity.toDomain(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        uri = uri,
        size = size,
        pages = pages,
        progress = progress,
        timestamp = timestamp
    )
}

fun Book.toEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        author = author,
        uri = uri,
        size = size,
        pages = pages,
        progress = progress,
        timestamp = timestamp
    )
}
