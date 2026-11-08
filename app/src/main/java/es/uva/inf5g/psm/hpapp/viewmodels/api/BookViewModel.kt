package es.uva.inf5g.psm.hpapp.viewmodels.api

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uva.inf5g.psm.hpapp.modelos.repository.api.BooksRepository
import es.uva.inf5g.psm.hpapp.modelos.estadospeticiones.BooksFetchState
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BooksRepository) : ViewModel() {

    private val _booksState = mutableStateOf(BooksFetchState())
    val booksState: State<BooksFetchState> = _booksState


    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            _booksState.value = _booksState.value.copy(loading = true, error = null)
            try {
                val books = repository.getBooks()
                _booksState.value = _booksState.value.copy(
                    loading = false,
                    bookData = books
                )
            } catch (e: Exception) {
                _booksState.value = _booksState.value.copy(
                    loading = false,
                    error = "Error obteniendo los libros: ${e.message}"
                )
            }
        }
    }

}
