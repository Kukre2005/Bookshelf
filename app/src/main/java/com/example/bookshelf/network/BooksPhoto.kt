package com.example.bookshelf.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)