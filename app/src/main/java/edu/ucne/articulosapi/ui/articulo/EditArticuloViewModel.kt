package edu.ucne.articulosapi.ui.articulo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.remote.ArticuloRepository
import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditArticuloViewModel @Inject constructor(
    private val repository: ArticuloRepository
): ViewModel() {

    var descripcion by mutableStateOf("")
    var marca by mutableStateOf("")
    var existencia by mutableStateOf("")

    var currenId = get<string>("ariticuloId").

    init {
        viewModelScope.launch {
            val articulo = repository.getArticulo(currenId)

            if (articulo != null) {
                descripcion = articulo.descripcion
                marca = articulo.marca
                existencia = articulo.existencia.toString()
            }
        }
    }

    fun save(articulo: ArticulosResponseDto){
        viewModelScope.launch {
            repository.createArticulo(articulo)
        }
    }

}