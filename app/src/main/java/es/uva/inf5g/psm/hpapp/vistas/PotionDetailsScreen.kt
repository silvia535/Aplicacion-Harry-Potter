package es.uva.inf5g.psm.hpapp.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import es.uva.inf5g.psm.hpapp.HogwartsBlue
import es.uva.inf5g.psm.hpapp.HogwartsGold
import es.uva.inf5g.psm.hpapp.R
import es.uva.inf5g.psm.hpapp.modelos.potions.PotionAttributes

@Composable
fun PotionDetailsScreen(
    potion: PotionAttributes,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(HogwartsBlue)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        // Imagen de la poción
        if (potion.image.isNullOrEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.missing_potion),
                contentDescription = "Missing Potion",
                modifier = Modifier
                    .height(260.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(12.dp))
            )
        } else {
            AsyncImage(
                model = potion.image,
                contentDescription = potion.name ?: "Potion Image",
                modifier = Modifier
                    .height(260.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre de la poción
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = potion.name ?: "Unknown",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Información básica
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = "Difficulty: ${potion.difficulty ?: "Unknown"}",
                    color = Color.Black
                )
                Text(
                    text = "Effect: ${potion.effect ?: "Unknown"}",
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // INGREDIENTES
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = "Ingredients",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))

                potion.ingredients
                    ?.split(',', ';') // separa por coma o punto y coma
                    ?.map { it.trim() } // elimina espacios
                    ?.filter { it.isNotEmpty() } // descarta strings vacíos
                    ?.forEach { ingredient ->
                        Text(
                            text = "★ $ingredient",
                            color = Color.Black
                        )
                    } ?: Text(
                    text = "★ Unknown",
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // CARACTERÍSTICAS
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = "Characteristics",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))

                potion.characteristics
                    ?.split(',', ';')
                    ?.map { it.trim() }
                    ?.filter { it.isNotEmpty() }
                    ?.forEach { characteristic ->
                        Text(
                            text = "★ $characteristic",
                            color = Color.Black
                        )
                    } ?: Text(
                    text = "★ Unknown",
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
