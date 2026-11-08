package es.uva.inf5g.psm.hpapp.modelos.potions

import es.uva.inf5g.psm.hpapp.modelos.Links


data class PotionData(
    val id : String,
    val type : String,
    val attributes : PotionAttributes,
    val links : Links
)