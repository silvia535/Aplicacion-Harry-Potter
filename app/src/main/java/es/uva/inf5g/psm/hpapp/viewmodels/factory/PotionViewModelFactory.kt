package es.uva.inf5g.psm.hpapp.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uva.inf5g.psm.hpapp.modelos.repository.api.PotionsRepository
import es.uva.inf5g.psm.hpapp.viewmodels.api.PotionViewModel

class PotionViewModelFactory(
    private val repository: PotionsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PotionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PotionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}