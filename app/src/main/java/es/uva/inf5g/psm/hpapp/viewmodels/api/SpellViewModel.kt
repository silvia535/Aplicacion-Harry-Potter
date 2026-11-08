package es.uva.inf5g.psm.hpapp.viewmodels.api

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uva.inf5g.psm.hpapp.modelos.estadospeticiones.SpellsFetchState
import es.uva.inf5g.psm.hpapp.modelos.repository.api.SpellsRepository
import kotlinx.coroutines.launch

class SpellViewModel(
    private val repository: SpellsRepository
) : ViewModel() {

    private val _spellsState = mutableStateOf(SpellsFetchState())
    val spellsState: State<SpellsFetchState> = _spellsState

    init {
        fetchSpells()
    }

    /** Llama a la API usando la página y el query actuales */
    private fun fetchSpells() {
        viewModelScope.launch {
            _spellsState.value = _spellsState.value.copy(
                loading = true,
                error = null
            )

            try {
                val state = _spellsState.value

                val response = repository.getSpells(
                    page = state.currentPage,
                    size = state.pageSize,
                    name = state.searchQuery.takeIf { it.isNotBlank() }
                )

                _spellsState.value = state.copy(
                    loading = false,
                    spells = response.data,
                    hasPrevPage = state.currentPage > 1,
                    hasNextPage = response.data.size == state.pageSize
                )

            } catch (e: Exception) {
                _spellsState.value = _spellsState.value.copy(
                    loading = false,
                    error = "Error obteniendo hechizos: ${e.message}"
                )
            }
        }
    }

    /** Pasa a la siguiente página y llama a la API */
    fun nextSpellsPage() {
        val state = _spellsState.value
        if (state.hasNextPage) {
            _spellsState.value = state.copy(
                currentPage = state.currentPage + 1
            )
            fetchSpells()
        }
    }

    /** Pasa a la página anterior y llama a la API */
    fun prevSpellsPage() {
        val state = _spellsState.value
        if (state.hasPrevPage) {
            _spellsState.value = state.copy(
                currentPage = state.currentPage - 1
            )
            fetchSpells()
        }
    }

    /** Actualiza el query local (sin llamar a la API) */
    fun updateSpellsSearch(query: String) {
        _spellsState.value = _spellsState.value.copy(
            searchQuery = query
        )
    }

    /** Lanza la búsqueda al pulsar el botón Buscar */
    fun searchSpells() {
        _spellsState.value = _spellsState.value.copy(
            currentPage = 1
        )
        fetchSpells()
    }
}
