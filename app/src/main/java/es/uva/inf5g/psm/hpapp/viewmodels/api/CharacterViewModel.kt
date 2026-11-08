package es.uva.inf5g.psm.hpapp.viewmodels.api

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uva.inf5g.psm.hpapp.modelos.estadospeticiones.CharactersFetchState
import es.uva.inf5g.psm.hpapp.modelos.repository.api.CharactersRepository
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    private val _charactersState = mutableStateOf(CharactersFetchState())
    val charactersState: State<CharactersFetchState> = _charactersState

    init {
        fetchCharacters()
    }

    /** Llama a la API usando la página y el query actuales */
    private fun fetchCharacters() {
        viewModelScope.launch {
            _charactersState.value = _charactersState.value.copy(
                loading = true,
                error = null
            )

            try {
                val state = _charactersState.value

                val response = repository.getCharacters(
                    page = state.currentPage,
                    size = state.pageSize,
                    name = state.searchQuery.takeIf { it.isNotBlank() }
                )

                _charactersState.value = state.copy(
                    loading = false,
                    characters = response.data,
                    hasPrevPage = state.currentPage > 1,
                    hasNextPage = response.data.size == state.pageSize
                )

            } catch (e: Exception) {
                _charactersState.value = _charactersState.value.copy(
                    loading = false,
                    error = "Error obteniendo personajes: ${e.message}"
                )
            }
        }
    }

    /** Pasa a la siguiente página y llama a la API */
    fun nextCharactersPage() {
        val state = _charactersState.value
        if (state.hasNextPage) {
            _charactersState.value = state.copy(
                currentPage = state.currentPage + 1
            )
            fetchCharacters()
        }
    }

    /** Pasa a la página anterior y llama a la API */
    fun prevCharactersPage() {
        val state = _charactersState.value
        if (state.hasPrevPage) {
            _charactersState.value = state.copy(
                currentPage = state.currentPage - 1
            )
            fetchCharacters()
        }
    }

    /** Actualiza el query local (sin llamar a la API) */
    fun updateCharacterSearch(query: String) {
        _charactersState.value = _charactersState.value.copy(
            searchQuery = query
        )
    }

    /** Lanza la búsqueda al pulsar el botón Buscar */
    fun searchCharacters() {
        _charactersState.value = _charactersState.value.copy(
            currentPage = 1
        )
        fetchCharacters()
    }
}
