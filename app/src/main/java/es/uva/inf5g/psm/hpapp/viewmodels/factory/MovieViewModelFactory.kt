package es.uva.inf5g.psm.hpapp.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uva.inf5g.psm.hpapp.modelos.repository.api.MoviesRepository
import es.uva.inf5g.psm.hpapp.viewmodels.api.MovieViewModel

class MovieViewModelFactory(private val repository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}