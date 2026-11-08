package es.uva.inf5g.psm.hpapp.modelos.repository.api

import es.uva.inf5g.psm.hpapp.APIService
import es.uva.inf5g.psm.hpapp.modelos.potions.PotionsResponse

class PotionsRepository(private val api: APIService) {

    suspend fun getPotions(
        page: Int,
        size: Int,
        name: String? = null
    ): PotionsResponse {
        return api.getPotions(
            page = page,
            size = size,
            name = name
        )
    }
}