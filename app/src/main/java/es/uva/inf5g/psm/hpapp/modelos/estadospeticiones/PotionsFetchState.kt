package es.uva.inf5g.psm.hpapp.modelos.estadospeticiones

import es.uva.inf5g.psm.hpapp.modelos.potions.PotionData

data class PotionsFetchState (
    val loading: Boolean = false,
    val potions: List<PotionData> = emptyList(),
    val searchQuery: String = "",
    val currentPage: Int = 1,
    val pageSize: Int = 20,
    val hasNextPage: Boolean = false,
    val hasPrevPage: Boolean = false,
    val error: String? = null
)