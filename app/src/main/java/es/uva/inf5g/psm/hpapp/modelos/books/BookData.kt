package es.uva.inf5g.psm.hpapp.modelos.books


import es.uva.inf5g.psm.hpapp.modelos.Links


data class BookData(
    val id: String,
    val type: String,
    val attributes: BookAttributes,
    val relationships: Relationships?,
    val links: Links?
)