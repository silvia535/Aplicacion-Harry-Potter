package es.uva.inf5g.psm.hpapp.viewmodels.api

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uva.inf5g.psm.hpapp.modelos.estadospeticiones.MoviesFetchState
import es.uva.inf5g.psm.hpapp.modelos.repository.api.MoviesRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _moviesState = mutableStateOf(MoviesFetchState())
    val moviesState: State<MoviesFetchState> = _moviesState

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _moviesState.value = _moviesState.value.copy(loading = true, error = null)
            try {
                val movies = repository.getHarryPotterMovies()
                _moviesState.value = _moviesState.value.copy(
                    loading = false,
                    movieData = movies
                )
            } catch (e: Exception) {
                _moviesState.value = _moviesState.value.copy(
                    loading = false,
                    error = "Error obteniendo las películas: ${e.message}"
                )
            }
        }
    }
}
