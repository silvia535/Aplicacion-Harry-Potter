package es.uva.inf5g.psm.hpapp.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import es.uva.inf5g.psm.hpapp.HogwartsBlue
import es.uva.inf5g.psm.hpapp.HogwartsGold
import es.uva.inf5g.psm.hpapp.modelos.movies.MovieAttributes
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MovieDetailsScreen(
    movie: MovieAttributes,
    modifier: Modifier = Modifier
) {

    // Formateador de fecha: AAAA-MM-DD
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formattedDate = movie.release_date
        ?.let { dateFormatter.format(it) }
        ?: "Fecha desconocida"

    // Directores sin []
    val directorsFormatted = movie.directors.joinToString(", ")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(HogwartsBlue)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        // Poster de la película
        AsyncImage(
            model = movie.poster,
            contentDescription = movie.title,
            modifier = Modifier
                .height(260.dp)
                .aspectRatio(0.7f)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = movie.title ?: "Sin título",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Información de la película
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = "Release date: $formattedDate",
                    color = Color.Black
                )
                Text(
                    text = "Director: $directorsFormatted",
                    color = Color.Black
                )
                Text(
                    text = "Box Office: ${movie.box_office}",
                    color = Color.Black
                )
                Text(
                    text = "Budget: ${movie.budget}",
                    color = Color.Black
                )
                Text(
                    text = "Rating: ${movie.rating}",
                    color = Color.Black
                )
                Text(
                    text = "Duration: ${movie.running_time}",
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Resumen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HogwartsGold, RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = movie.summary,
                color = Color.Black,
                textAlign = TextAlign.Justify
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
