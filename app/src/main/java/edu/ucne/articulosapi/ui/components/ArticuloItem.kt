package edu.ucne.articulosapi.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import java.text.DecimalFormat


@Composable
fun ArticuloItem(
    modifier: Modifier = Modifier,
    articulo: ArticulosResponseDto,
    onEditArticulo: () -> Unit,
    onDeleteArticulo: () -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = articulo.descripcion,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = articulo.marca,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(4.dp))

                val decimal = DecimalFormat("#,###.######")
                Text(
                    text =  "$ ${decimal.format(articulo.precio)}",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text =  articulo.existencia.toString(),
                    style = MaterialTheme.typography.h6
                )
            }

            Row {

                IconButton(onClick = onEditArticulo ){
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }

                IconButton(onClick = onDeleteArticulo ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }

            }

        }
    }
}