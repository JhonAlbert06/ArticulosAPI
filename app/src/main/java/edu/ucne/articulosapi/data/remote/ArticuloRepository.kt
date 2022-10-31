package edu.ucne.prestamospersonales.data.remote

import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticuloRepository @Inject constructor(
    private val api: ArticulosApi
) {
    suspend fun getArticulos(): List<ArticulosResponseDto> {
        return withContext(Dispatchers.IO){
            val resonse = api.GetArticulos()
            resonse.body()?: emptyList()
        }
    }

    suspend fun getArticulo(id: String?): ArticulosResponseDto? {
        return withContext(Dispatchers.IO){
            val response = api.GetArticulo(id ?: "")
            response.body()
        }
    }

    suspend fun createArticulo(articulo: ArticulosResponseDto): ArticulosResponseDto? {
        return withContext(Dispatchers.IO){
            val response = api.CreateArticulo(articulo)
            response.body()
        }
    }

    suspend fun deleteArticulo(id: String): Boolean {
        return withContext(Dispatchers.IO){
            val response = api.DeleteArticulo(id)
            response.isSuccessful
        }
    }

    suspend fun updateArticulo(id: String, articulo: ArticulosResponseDto): ArticulosResponseDto? {
        return withContext(Dispatchers.IO){
            val response = api.UpdateArticulo(id,articulo)
            response.body()
        }
    }

}