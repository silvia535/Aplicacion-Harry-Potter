package es.uva.inf5g.psm.hpapp.modelos.estadospeticiones

import es.uva.inf5g.psm.hpapp.modelos.movies.MovieData

data class MoviesFetchState(
    val loading: Boolean = true,
    val movieData: List<MovieData> = emptyList(),
    val error: String? = null
)