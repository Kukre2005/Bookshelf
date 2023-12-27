package com.example.bookshelf.network

import retrofit2.http.GET

interface BooksApiService {
    @GET("books")
    suspend fun getBooks(): List<BooksPhoto>
}