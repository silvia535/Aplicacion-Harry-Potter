package es.uva.inf5g.psm.hpapp.modelos.repository.api

import es.uva.inf5g.psm.hpapp.APIService
import es.uva.inf5g.psm.hpapp.modelos.books.BookData

class BooksRepository(private val api: APIService) {


    suspend fun getBooks(): List<BookData> {
        val response = api.getBooks()
        return response.data
    }

}
