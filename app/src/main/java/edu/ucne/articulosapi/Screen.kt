package edu.ucne.articulosapi

sealed class Screen(val route: String){

    object PrincipalScreen: Screen("PrincipalScreen")

    object HomeArticulo: Screen("HomeArticuloScreen")
    object EditArticulo: Screen("EditArticuloScreen?ariticuloId={ariticuloId}"){
        fun passId(ariticuloId: Int?): String {
            return "EditArticuloScreen?ariticuloId=${ariticuloId.toString()}"
        }
    }
}