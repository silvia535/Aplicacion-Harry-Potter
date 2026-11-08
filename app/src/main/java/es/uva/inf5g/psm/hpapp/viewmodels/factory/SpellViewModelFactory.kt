package es.uva.inf5g.psm.hpapp.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uva.inf5g.psm.hpapp.modelos.repository.api.SpellsRepository
import es.uva.inf5g.psm.hpapp.viewmodels.api.SpellViewModel

class SpellViewModelFactory(
    private val repository: SpellsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpellViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SpellViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}