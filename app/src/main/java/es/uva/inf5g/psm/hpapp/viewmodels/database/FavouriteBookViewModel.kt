package es.uva.inf5g.psm.hpapp.viewmodels.database

import androidx.lifecycle.ViewModel
import es.uva.inf5g.psm.hpapp.modelos.books.BookAttributes
import es.uva.inf5g.psm.hpapp.modelos.books.FavouriteBookDoc
import es.uva.inf5g.psm.hpapp.modelos.repository.database.FavouriteBooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavouriteBookViewModel : ViewModel(){
    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    private val _favouritesBooks = MutableStateFlow<List<FavouriteBookDoc>>(emptyList())
    val favourites = _favouritesBooks.asStateFlow()

    fun addFavouriteBook(book: BookAttributes) {
        FavouriteBooksRepository.addFavouriteBook(book) { ok, msg ->
            if (ok) {
                loadFavouritesBooks()
            }
        }
    }



    fun loadFavouritesBooks() {
        FavouriteBooksRepository.getFavouritesBooks(
            onResult = { list -> _favouritesBooks.value = list },
            onError = { e -> _message.value = e.message ?: "Error al cargar libros favoritos" }
        )
    }

    fun deleteFavouritesBooks(id: String) {
        FavouriteBooksRepository.deleteFavouriteBook(id) { ok, msg ->
            if (ok) loadFavouritesBooks()
        }
    }
}
