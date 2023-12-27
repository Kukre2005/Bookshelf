package com.example.bookshelf.ui.theme.screens

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.network.BooksPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookUiState {
    data class Success(val photo: List<BooksPhoto>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class BooksViewModel(private val booksRepository: BookshelfRepository) : ViewModel() {
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)

    init {
        getBooksPhoto()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getBooksPhoto() {
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            bookUiState = try {
                BookUiState.Success(booksRepository.getBooks())
            }catch (e: IOException) {
                BookUiState.Error
            }catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val booksRepository = application.container.bookshelfRepository
                BooksViewModel(booksRepository = booksRepository)
            }
        }
    }
}