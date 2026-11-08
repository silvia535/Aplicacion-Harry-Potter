package es.uva.inf5g.psm.hpapp.modelos.chapters

import es.uva.inf5g.psm.hpapp.modelos.Links
import es.uva.inf5g.psm.hpapp.modelos.books.Relationships

data class ChapterData(
    val id: String,
    val type: String,
    val attributes: ChapterAttributes,
    val relationships: Relationships,
    val links : Links?
)