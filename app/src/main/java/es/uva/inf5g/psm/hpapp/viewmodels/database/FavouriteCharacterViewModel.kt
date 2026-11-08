package es.uva.inf5g.psm.hpapp.viewmodels.database

import androidx.lifecycle.ViewModel
import es.uva.inf5g.psm.hpapp.modelos.characters.CharacterAttributes
import es.uva.inf5g.psm.hpapp.modelos.characters.FavouriteCharacterDoc
import es.uva.inf5g.psm.hpapp.modelos.repository.database.FavouriteCharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavouriteCharacterViewModel : ViewModel() {
    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    private val _favouritesCharacters = MutableStateFlow<List<FavouriteCharacterDoc>>(emptyList())
    val favourites = _favouritesCharacters.asStateFlow()

    fun addFavouriteCharacter(character: CharacterAttributes) {
        FavouriteCharactersRepository.addFavouriteCharacter(character){ ok, msg ->
            if (ok) {
                loadFavouritesCharacters()
            }
        }
    }

    fun loadFavouritesCharacters() {
        FavouriteCharactersRepository.getFavouritesCharacters(
            onResult = { list -> _favouritesCharacters.value = list },
            onError = { e -> _message.value = e.message ?: "Error al cargar personajes favoritos" }
        )
    }

    fun deleteFavouritesCharacter(id: String) {
        FavouriteCharactersRepository.deleteFavouriteCharacter(id) { ok, msg ->
            if (ok) loadFavouritesCharacters()
        }
    }

}