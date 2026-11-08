package es.uva.inf5g.psm.hpapp.modelos.estadospeticiones

import es.uva.inf5g.psm.hpapp.modelos.books.BookData

data class BooksFetchState(
    val loading: Boolean = true,
    val bookData: List<BookData> = emptyList(),
    val error: String? = null
)