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
import es.uva.inf5g.psm.hpapp.modelos.characters.CharacterAttributes

@Composable
fun CharacterDetailsScreen(
    character: CharacterAttributes,
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

        // Imagen del personaje centrada
        if (character.image.isNullOrEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.missing_character),
                contentDescription = "Missing Character",
                modifier = Modifier
                    .height(260.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(12.dp))
            )
        } else {
            AsyncImage(
                model = character.image,
                contentDescription = character.name ?: "Character Image",
                modifier = Modifier
                    .height(260.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre del personaje
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = character.name ?: "Unknown",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Información del personaje
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Column {
                Text(text = "Born: ${character.born ?: "Unknown"}", color = Color.Black)
                Text(text = "Gender: ${character.gender ?: "Unknown"}", color = Color.Black)
                Text(text = "Species: ${character.species ?: "Unknown"}", color = Color.Black)
                Text(text = "Nationality: ${character.nationality ?: "Unknown"}", color = Color.Black)
                Text(text = "Blood Status: ${character.blood_status ?: "Unknown"}", color = Color.Black)
                Text(text = "House: ${character.house ?: "Unknown"}", color = Color.Black)
                Text(text = "Hair color: ${character.hair_color ?: "Unknown"}", color = Color.Black)
                Text(text = "Eye color: ${character.eye_color ?: "Unknown"}", color = Color.Black)
                Text(text = "Boggart: ${character.boggart ?: "Unknown"}", color = Color.Black)
                Text(text = "Patronus: ${character.patronus ?: "Unknown"}", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}
