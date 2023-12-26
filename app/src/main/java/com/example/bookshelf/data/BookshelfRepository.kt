package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService

interface BookshelfRepository {
    suspend fun getBooks(): List<Books>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
): BookshelfRepository {
    override suspend fun getBooks(): List<Books> = booksApiService.getBooks()
}