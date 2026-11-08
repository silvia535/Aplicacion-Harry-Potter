package es.uva.inf5g.psm.hpapp.viewmodels.database

import androidx.lifecycle.ViewModel
import es.uva.inf5g.psm.hpapp.modelos.potions.FavouritePotionDoc
import es.uva.inf5g.psm.hpapp.modelos.potions.PotionAttributes
import es.uva.inf5g.psm.hpapp.modelos.repository.database.FavouritePotionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavouritePotionViewModel : ViewModel(){
    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    private val _favouritesPotions = MutableStateFlow<List<FavouritePotionDoc>>(emptyList())
    val favourites = _favouritesPotions.asStateFlow()

    fun addFavouritePotion(potion: PotionAttributes) {
        FavouritePotionsRepository.addFavouritePotion(potion){ ok, msg ->
            if (ok) {
                loadFavouritesPotions()
            }
        }
    }



    fun loadFavouritesPotions() {
        FavouritePotionsRepository.getFavouritesPotions(
            onResult = { list -> _favouritesPotions.value = list },
            onError = { e -> _message.value = e.message ?: "Error al cargar pociones favoritas" }
        )
    }

    fun deleteFavouritesPotion(id: String) {
        FavouritePotionsRepository.deleteFavouritePotion(id) { ok, msg ->
            if (ok) loadFavouritesPotions()
        }
    }

}
