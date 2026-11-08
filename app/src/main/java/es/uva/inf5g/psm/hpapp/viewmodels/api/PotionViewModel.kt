package es.uva.inf5g.psm.hpapp.viewmodels.api

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uva.inf5g.psm.hpapp.modelos.estadospeticiones.PotionsFetchState
import es.uva.inf5g.psm.hpapp.modelos.repository.api.PotionsRepository
import kotlinx.coroutines.launch

class PotionViewModel(
    private val repository: PotionsRepository
) : ViewModel() {

    private val _potionsState = mutableStateOf(PotionsFetchState())
    val potionsState: State<PotionsFetchState> = _potionsState

    init {
        fetchPotions()
    }

    /** Llama a la API usando la página y el query actuales */
    private fun fetchPotions() {
        viewModelScope.launch {
            _potionsState.value = _potionsState.value.copy(
                loading = true,
                error = null
            )

            try {
                val state = _potionsState.value

                val response = repository.getPotions(
                    page = state.currentPage,
                    size = state.pageSize,
                    name = state.searchQuery.takeIf { it.isNotBlank() }
                )

                _potionsState.value = state.copy(
                    loading = false,
                    potions = response.data,
                    hasPrevPage = state.currentPage > 1,
                    hasNextPage = response.data.size == state.pageSize
                )

            } catch (e: Exception) {
                _potionsState.value = _potionsState.value.copy(
                    loading = false,
                    error = "Error obteniendo pociones: ${e.message}"
                )
            }
        }
    }

    /** Pasa a la siguiente página y llama a la API */
    fun nextPotionsPage() {
        val state = _potionsState.value
        if (state.hasNextPage) {
            _potionsState.value = state.copy(
                currentPage = state.currentPage + 1
            )
            fetchPotions()
        }
    }

    /** Pasa a la página anterior y llama a la API */
    fun prevPotionsPage() {
        val state = _potionsState.value
        if (state.hasPrevPage) {
            _potionsState.value = state.copy(
                currentPage = state.currentPage - 1
            )
            fetchPotions()
        }
    }

    /** Actualiza el query local (sin llamar a la API) */
    fun updatePotionSearch(query: String) {
        _potionsState.value = _potionsState.value.copy(
            searchQuery = query
        )
    }

    /** Lanza la búsqueda al pulsar el botón Buscar */
    fun searchPotions() {
        _potionsState.value = _potionsState.value.copy(
            currentPage = 1
        )
        fetchPotions()
    }
}
