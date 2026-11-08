package es.uva.inf5g.psm.hpapp.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uva.inf5g.psm.hpapp.modelos.repository.api.BooksRepository
import es.uva.inf5g.psm.hpapp.viewmodels.api.BookViewModel

class BookViewModelFactory(private val repository: BooksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}