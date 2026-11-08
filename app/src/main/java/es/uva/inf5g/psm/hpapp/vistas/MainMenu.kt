package es.uva.inf5g.psm.hpapp.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.uva.inf5g.psm.hpapp.HPLogo
import es.uva.inf5g.psm.hpapp.R
import es.uva.inf5g.psm.hpapp.*

@Composable
fun MainMenu(
    onNavigateToItemScreen: (String) -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    val harryPotterFont = FontFamily(Font(R.font.harry_potter_font))
    val categorias = listOf("Libros", "Películas", "Personajes", "Pociones", "Hechizos")

    // Detectar orientación
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    // Cambiar columnas según orientación
    val columnas = if (isLandscape) 4 else 2

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HogwartsBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HPLogo()

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(columnas),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(categorias) { categoria ->
                MenuBox(
                    label = categoria,
                    font = harryPotterFont,
                    boxColor = HogwartsGold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isLandscape) 100.dp else 120.dp),
                    onClick = { onNavigateToItemScreen(categoria) }
                )
            }

            item {
                MenuBox(
                    label = "Favoritos",
                    font = harryPotterFont,
                    boxColor = HogwartsGold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isLandscape) 100.dp else 120.dp),
                    onClick = onNavigateToFavorites
                )
            }
        }
    }
}

@Composable
fun MenuBox(
    label: String,
    font: FontFamily,
    boxColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(boxColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontFamily = font,
            fontSize = 22.sp,
            color = Color.Black
        )
    }
}
