package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService
import com.example.bookshelf.network.BooksPhoto

interface BookshelfRepository {
    suspend fun getBooks(): List<BooksPhoto>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
): BookshelfRepository {
    override suspend fun getBooks(): List<BooksPhoto> = booksApiService.getBooks()
}