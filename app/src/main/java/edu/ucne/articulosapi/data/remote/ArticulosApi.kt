package edu.ucne.prestamospersonales.data.remote

import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import retrofit2.Response
import retrofit2.http.*

interface ArticulosApi{
    @GET("api/Articulos")
    suspend fun GetArticulos(): Response<List<ArticulosResponseDto>>

    @GET("api/Articulos/{id}")
    suspend fun GetArticulo(@Path("id") id: String): Response<ArticulosResponseDto>

    @POST("api/Articulos")
    suspend fun CreateArticulo(@Body articulo: ArticulosResponseDto): Response<ArticulosResponseDto>

    @DELETE("api/Articulos/{id}")
    suspend fun DeleteArticulo(@Path("id") id: String): Response<ArticulosResponseDto>

    @PUT("api/Articulos/{id}")
    suspend fun UpdateArticulo(@Path("id") id: String, @Body articulo: ArticulosResponseDto): Response<ArticulosResponseDto>
}