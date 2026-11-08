package es.uva.inf5g.psm.hpapp.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uva.inf5g.psm.hpapp.modelos.repository.api.CharactersRepository
import es.uva.inf5g.psm.hpapp.viewmodels.api.CharacterViewModel

class CharacterViewModelFactory(
    private val repository: CharactersRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}