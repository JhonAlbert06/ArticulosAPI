package edu.ucne.articulosapi.ui.articulo

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import edu.ucne.articulosapi.ui.components.InputText
import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun EditArticuloScreen(
    navController: NavHostController,
    viewModel: EditArticuloViewModel = hiltViewModel(),
) {
    var isError by mutableStateOf(true)

    Scaffold(

        topBar = {
            EditAuxTopBar(
                topAppBarText = "Añadir Articulos"
            )
        },

        content = {
            isError = validacion(viewModel = viewModel)
        },

        bottomBar = {
            EditBottomBar(navController,
                onInsertArticulo = {viewModel.save(
                    ArticulosResponseDto(
                        descripcion = viewModel.descripcion,
                        marca = viewModel.marca,
                        existencia = viewModel.existencia.toDouble()
                    )
                )},
                isError = isError
            )
        }

    )
}

@Composable
fun EditBottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onInsertArticulo: () -> Unit,
    isError: Boolean = true
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 18.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.Green),
        onClick = { if (isError){
            onInsertArticulo()
            navController.navigateUp()
        }  },
        enabled = isError

    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = null
        )
        Text(text = "Añadir Aux")
    }
}

@Composable
fun EditConten(
    viewModel: EditArticuloViewModel,
    isErrorExistencia: Boolean,
    isErrorMarca: Boolean,
    isErrorDescripcion: Boolean,
    mgsIsErrorExistencia: String,
    mgsIrrorMarca: String,
    mgsIrrorDescripcion: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        InputText(
            isError = isErrorDescripcion,
            msgError = mgsIrrorDescripcion,
            value = viewModel.descripcion,
            onValueChange = { viewModel.descripcion = it },
            label = "Descripcion",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(5.dp))

        InputText(
            isError = isErrorMarca,
            msgError = mgsIrrorMarca,
            value = viewModel.marca,
            onValueChange = { viewModel.marca = it },
            label = "Marca",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(5.dp))

        InputText(
            isError = isErrorExistencia,
            msgError = mgsIsErrorExistencia,
            value = viewModel.existencia,
            onValueChange = { viewModel.existencia = it },
            label = "Existencia",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(5.dp))

    }

}

@Composable
fun EditAuxTopBar(topAppBarText: String) {

    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )

}

fun isNumber(aux: String): Boolean {
    return try {
        aux.toDouble()
        false
    } catch (e: java.lang.NumberFormatException) {
        true
    }
}

@Composable
fun validacion(viewModel: EditArticuloViewModel): Boolean {

    var isErrorExistencia: Boolean = false
    var isErrorMarca: Boolean = false
    var isErrorDescripcion: Boolean = false
    var mgsIsErrorExistencia: String = ""
    var mgsIrrorMarca: String = ""
    var mgsIrrorDescripcion: String = ""

    if (viewModel.descripcion.isBlank()) {
        isErrorDescripcion = true
        mgsIrrorDescripcion = "*Campo Obligatorio*"
    } else if (viewModel.descripcion.isDigitsOnly()) {
        isErrorDescripcion = true
        mgsIrrorDescripcion = "*Descripcion invalidad(Solo puede contener letras)*"
    } else if (viewModel.descripcion.length in 1..4) {
        isErrorDescripcion = true
        mgsIrrorDescripcion = "*La descripcion debe contener minimo(5) Caracteres*"
    }

    if (viewModel.marca.isBlank()) {
        isErrorMarca = true
        mgsIrrorMarca = "*Campo Obligatorio*"
    } else if (viewModel.marca.isDigitsOnly()) {
        isErrorMarca = true
        mgsIrrorMarca = "*Descripcion invalidad(Solo puede contener letras)*"
    } else if (viewModel.marca.length in 1..4) {
        isErrorMarca = true
        mgsIrrorMarca = "*La descripcion debe contener minimo(5) Caracteres*"
    }

    if (viewModel.existencia.isBlank()) {
        isErrorExistencia = true
        mgsIsErrorExistencia = "*Campo Obligatorio*"
    } else if (isNumber(viewModel.existencia)) {
        isErrorExistencia = true
        mgsIsErrorExistencia = "*Esto no es un Numero*"
    }

    EditConten(
        viewModel = viewModel,
        isErrorExistencia = isErrorExistencia,
        isErrorMarca = isErrorMarca,
        isErrorDescripcion = isErrorDescripcion,
        mgsIsErrorExistencia = mgsIsErrorExistencia,
        mgsIrrorMarca = mgsIrrorMarca,
        mgsIrrorDescripcion = mgsIrrorDescripcion
    )

    return !(isErrorExistencia && isErrorDescripcion && isErrorMarca)
}