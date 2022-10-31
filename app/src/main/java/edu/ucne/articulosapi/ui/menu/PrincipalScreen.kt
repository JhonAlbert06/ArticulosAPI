package edu.ucne.articulosapi.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.ucne.articulosapi.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PrincipalScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navigationItems = listOf(
        Destinos.Articulos
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MenuTopBar(scope, scaffoldState) },
        drawerContent = { Menu(navigationItems, navController) }
    ) {

    }
}

@Composable
fun Menu(
    navigationItems: List<Destinos>,
    navController: NavHostController
) {
    Column() {
        Image(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Logo",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
        )
        navigationItems.forEach { item ->
            MenuItem(item = item, navController)
        }
    }
}

@Composable
fun MenuItem(
    item: Destinos,
    navController: NavHostController,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .padding(8.dp)
            .clickable { navController.navigate(item.ruta) }
    ) {
        Image(
            imageVector = item.icon,
            contentDescription = item.title,
            modifier = Modifier
                .height(80.dp)
                .width(55.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.CenterStart),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
    }
}

@Composable
fun MenuTopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
        title = { Text(text = "Prestamos Personales") },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Icono Menu"
                )
            }
        }
    )
}


sealed class Destinos(
    val icon: ImageVector,
    val title: String,
    val ruta: String
) {

    object Articulos : Destinos(
        Icons.Filled.Search,
        "Articulos",
        Screen.HomeArticulo.route
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMenu() {
    val navController = rememberNavController()
    PrincipalScreen(navController)
}