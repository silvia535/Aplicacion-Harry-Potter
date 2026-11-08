package es.uva.inf5g.psm.hpapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

val HogwartsGold = Color(0xFFD4AF37)
val HogwartsBlue = Color(0xFF001F3F)

@Composable
fun HPLogo(){
    Image(
        painter = painterResource(id = R.drawable.hp_logo),
        contentDescription = "Harry Potter Logo",
        modifier = Modifier.size(200.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CenterLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun CenterError(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Ocurrió un error: $message",
            color = Color.Red,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TwoColumnGrid(
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
    content: LazyGridScope.() -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = state,
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = content
    )
}

@Composable
fun MediaCard(
    imageUrl: String?,
    title: String,
    item: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.7f),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .background(HogwartsGold)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        HogwartsBlue,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (imageUrl.isNullOrEmpty()) {
                    when (item) {
                        "Personaje" -> Image(
                            painter = painterResource(id = R.drawable.missing_character),
                            contentDescription = "Missing Character",
                            modifier = Modifier.size(200.dp)
                        )
                        "Poción" -> Image(
                            painter = painterResource(id = R.drawable.missing_potion),
                            contentDescription = "Missing Potion",
                            modifier = Modifier.size(200.dp)
                        )
                        "Hechizo" -> Image(
                            painter = painterResource(id = R.drawable.missing_spell),
                            contentDescription = "Missing Spells",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = title,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }
            }

            Text(
                text = title,
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                maxLines = 2
            )
        }
    }
}


@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    item: String
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text("Buscar $item") },
        singleLine = true,
        textStyle = TextStyle(
            color = Color.White
        )
    )
}


@Composable
fun GoldenButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = HogwartsGold,
            contentColor = Color.Black,
            disabledContainerColor = HogwartsGold.copy(alpha = 0.4f),
            disabledContentColor = Color.Black.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PaginationControls(
    currentPage: Int,
    hasPrev: Boolean,
    hasNext: Boolean,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        GoldenButton(
            text = "Anterior",
            enabled = hasPrev,
            onClick = onPrev
        )

        Text(
            text = "Página $currentPage",
            color = HogwartsGold,
            fontWeight = FontWeight.SemiBold
        )

        GoldenButton(
            text = "Siguiente",
            enabled = hasNext,
            onClick = onNext
        )
    }
}





