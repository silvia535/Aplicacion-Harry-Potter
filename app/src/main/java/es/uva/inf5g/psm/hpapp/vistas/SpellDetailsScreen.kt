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
import es.uva.inf5g.psm.hpapp.modelos.spells.SpellAttributes

@Composable
fun SpellDetailsScreen(
    spell: SpellAttributes,
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
        if (spell.image.isNullOrEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.missing_spell),
                contentDescription = "Missing Potion",
                modifier = Modifier
                    .height(260.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(12.dp))
            )
        } else {
            AsyncImage(
                model = spell.image,
                contentDescription = spell.name ?: "Potion Image",
                modifier = Modifier
                    .height(260.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre del hechizo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = spell.name ?: "Unknown",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = spell.incantation ?: "Unknown",
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
                    text = "Effect: ${spell.effect ?: "Unknown"}",
                    color = Color.Black
                )
                Text(
                    text = "Category: ${spell.category ?: "Unknown"}",
                    color = Color.Black
                )
                Text(
                    text = "Light: ${spell.light ?: "Unknown"}",
                    color = Color.Black
                )
                Text(
                    text = "Hand: ${spell.hand ?: "Unknown"}",
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

    }
}
