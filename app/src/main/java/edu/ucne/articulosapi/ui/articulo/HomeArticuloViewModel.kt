package edu.ucne.articulosapi.ui.articulo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.remote.ArticuloRepository
import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

data class AuxListUiState(
    val articulos : List<ArticulosResponseDto> = emptyList()
)

@HiltViewModel
class HomeArticuloViewModel @Inject constructor(
    private val repository: ArticuloRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(AuxListUiState())
    val uiState : StateFlow<AuxListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(articulos = repository.getArticulos())
                }catch (ioe: IOException){
                    it.copy(articulos = emptyList())
                }
            }
        }
    }

    fun delete(articulo: ArticulosResponseDto) {
        viewModelScope.launch {
            repository.deleteArticulo(articulo.ariticuloId.toString())
        }
    }


}