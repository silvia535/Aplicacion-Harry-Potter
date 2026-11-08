package es.uva.inf5g.psm.hpapp.viewmodels.database


import androidx.lifecycle.ViewModel
import es.uva.inf5g.psm.hpapp.modelos.repository.database.FavouriteSpellsRepository
import es.uva.inf5g.psm.hpapp.modelos.spells.FavouriteSpellDoc
import es.uva.inf5g.psm.hpapp.modelos.spells.SpellAttributes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavouriteSpellViewModel : ViewModel() {
    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    private val _favouritesSpells = MutableStateFlow<List<FavouriteSpellDoc>>(emptyList())
    val favourites = _favouritesSpells.asStateFlow()

    fun addFavouriteSpell(spell: SpellAttributes) {
        FavouriteSpellsRepository.addFavouriteSpell(spell){ ok, msg ->
            if (ok) {
                loadFavouritesSpells()
            }
        }
    }



    fun loadFavouritesSpells() {
        FavouriteSpellsRepository.getFavouritesSpells(
            onResult = { list -> _favouritesSpells.value = list },
            onError = { e -> _message.value = e.message ?: "Error al cargar hechizos favoritos" }
        )
    }

    fun deleteFavouritesSpell(id: String) {
        FavouriteSpellsRepository.deleteFavouriteSpell(id) { ok, msg ->
            if (ok) loadFavouritesSpells()
        }
    }

}