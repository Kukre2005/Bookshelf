package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.*

interface AppContainer {
    val bookshelfRepository: BookshelfRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl =
        "https://books.google.com/books?hl=es&as_coll=0&num=10&uid=111744173425435357898&source=gbs_slider_cls_metadata_0_mylibrary_more"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }

    override val bookshelfRepository: BookshelfRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }
}