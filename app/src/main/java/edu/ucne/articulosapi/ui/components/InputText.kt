package edu.ucne.articulosapi.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputText(
    isError: Boolean,
    msgError: String,
    value: String,
    onValueChange: (String)->Unit,
    label: String,
    modifier: Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label)},
            modifier = modifier,
            singleLine = true
        )

        if (isError){
            Text(
                text = msgError,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewInput(){
    InputText(
        isError = true,
        msgError = "Campo Obligatorio",
        value = "Jhon",
        onValueChange = { /*TODO*/ },
        label = "Campo",
        modifier = Modifier.fillMaxWidth()
    )
}