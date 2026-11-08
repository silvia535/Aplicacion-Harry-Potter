package es.uva.inf5g.psm.hpapp.modelos.repository.api

import es.uva.inf5g.psm.hpapp.APIService
import es.uva.inf5g.psm.hpapp.modelos.movies.MovieData

class MoviesRepository(private val api: APIService) {
    suspend fun getHarryPotterMovies(): List<MovieData> {
        val response = api.getMovies()

        return response.data.filter { movie ->
            movie.attributes.title?.contains("Harry Potter", ignoreCase = true) == true
        }
    }
}