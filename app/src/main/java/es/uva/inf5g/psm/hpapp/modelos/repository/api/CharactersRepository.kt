package es.uva.inf5g.psm.hpapp.modelos.repository.api

import es.uva.inf5g.psm.hpapp.APIService
import es.uva.inf5g.psm.hpapp.modelos.characters.CharactersResponse


class CharactersRepository(private val api: APIService) {

    suspend fun getCharacters(
        page: Int,
        size: Int,
        name: String? = null
    ): CharactersResponse {
        return api.getCharacters(
            page = page,
            size = size,
            name = name
        )
    }
}
