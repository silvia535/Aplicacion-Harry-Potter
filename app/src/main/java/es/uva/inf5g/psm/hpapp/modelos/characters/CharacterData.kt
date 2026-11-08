package es.uva.inf5g.psm.hpapp.modelos.characters

import es.uva.inf5g.psm.hpapp.modelos.Links


data class CharacterData(
        val id : String,
        val type : String,
        val attributes : CharacterAttributes,
        val links: Links
)