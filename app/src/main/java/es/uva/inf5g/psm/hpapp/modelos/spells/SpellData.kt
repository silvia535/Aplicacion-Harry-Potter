package es.uva.inf5g.psm.hpapp.modelos.spells

import es.uva.inf5g.psm.hpapp.modelos.Links



data class SpellData (
    val id : String,
    val type : String,
    val attributes : SpellAttributes,
    val links : Links
)