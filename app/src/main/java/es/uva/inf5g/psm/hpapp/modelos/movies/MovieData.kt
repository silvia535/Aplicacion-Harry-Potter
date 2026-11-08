package es.uva.inf5g.psm.hpapp.modelos.movies


import es.uva.inf5g.psm.hpapp.modelos.Links

data class MovieData(
    val id : String,
    val type: String,
    val attributes: MovieAttributes,
    val links: Links?
)