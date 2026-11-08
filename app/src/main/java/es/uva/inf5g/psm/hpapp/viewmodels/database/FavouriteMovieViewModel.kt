package es.uva.inf5g.psm.hpapp.viewmodels.database

import androidx.lifecycle.ViewModel
import es.uva.inf5g.psm.hpapp.modelos.movies.FavouriteMovieDoc
import es.uva.inf5g.psm.hpapp.modelos.movies.MovieAttributes
import es.uva.inf5g.psm.hpapp.modelos.repository.database.FavouriteMoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavouriteMovieViewModel : ViewModel() {
    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    private val _favouritesMovies = MutableStateFlow<List<FavouriteMovieDoc>>(emptyList())
    val favourites = _favouritesMovies.asStateFlow()

    fun addFavouriteMovie(movie: MovieAttributes) {
        FavouriteMoviesRepository.addFavouriteMovie(movie){ ok, msg ->
            if (ok) {
                loadFavouritesMovies()
            }
        }
    }



    fun loadFavouritesMovies() {
        FavouriteMoviesRepository.getFavouritesMovies(
            onResult = { list -> _favouritesMovies.value = list },
            onError = { e -> _message.value = e.message ?: "Error al cargar películas favoritas" }
        )
    }

    fun deleteFavouritesMovies(id: String) {
        FavouriteMoviesRepository.deleteFavouriteMovie(id) { ok, msg ->
            if (ok) loadFavouritesMovies()
        }
    }

}