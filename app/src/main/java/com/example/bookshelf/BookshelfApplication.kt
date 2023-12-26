package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.data.AppContainer

class BookshelfApplication : Application() {
    private lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}