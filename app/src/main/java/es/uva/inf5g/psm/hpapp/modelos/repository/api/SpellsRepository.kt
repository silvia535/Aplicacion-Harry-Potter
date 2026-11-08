package es.uva.inf5g.psm.hpapp.modelos.repository.api

import es.uva.inf5g.psm.hpapp.APIService
import es.uva.inf5g.psm.hpapp.modelos.spells.SpellsResponse


class SpellsRepository(private val api: APIService) {
    suspend fun getSpells(
        page: Int,
        size: Int,
        name: String? = null
    ): SpellsResponse {
        return api.getSpells(
            page = page,
            size = size,
            name = name
        )
    }
}