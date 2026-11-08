package es.uva.inf5g.psm.hpapp.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import es.uva.inf5g.psm.hpapp.HogwartsBlue
import es.uva.inf5g.psm.hpapp.HogwartsGold
import es.uva.inf5g.psm.hpapp.modelos.books.BookAttributes
import es.uva.inf5g.psm.hpapp.modelos.books.FavouriteBookDoc
import es.uva.inf5g.psm.hpapp.modelos.characters.FavouriteCharacterDoc
import es.uva.inf5g.psm.hpapp.modelos.movies.FavouriteMovieDoc
import es.uva.inf5g.psm.hpapp.modelos.potions.FavouritePotionDoc
import es.uva.inf5g.psm.hpapp.modelos.repository.api.BooksRepository
import es.uva.inf5g.psm.hpapp.modelos.spells.FavouriteSpellDoc
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouriteBookViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouriteCharacterViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouriteMovieViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouritePotionViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouriteSpellViewModel

@Composable
fun FavouritesScreen() {

    // Libros favoritos
    val favouriteBooksVM: FavouriteBookViewModel = viewModel()
    val favouritesBooks by favouriteBooksVM.favourites.collectAsState()

    // Películas favoritas
    val favouriteMoviesVM: FavouriteMovieViewModel = viewModel()
    val favouritesMovies by favouriteMoviesVM.favourites.collectAsState()

    // Personajes favoritos
    val favouriteCharactersVM: FavouriteCharacterViewModel = viewModel()
    val favouritesCharacters by favouriteCharactersVM.favourites.collectAsState()

    //Pociones favoritas
    val favouritePotionsVM: FavouritePotionViewModel = viewModel()
    val favouritesPotions by favouritePotionsVM.favourites.collectAsState()

    //Hechizos favoritos
    val favouriteSpellsVM: FavouriteSpellViewModel = viewModel()
    val favouritesSpells by favouriteSpellsVM.favourites.collectAsState()

    LaunchedEffect(Unit) {
        favouriteBooksVM.loadFavouritesBooks()
        favouriteMoviesVM.loadFavouritesMovies()
        favouriteCharactersVM.loadFavouritesCharacters()
        favouritePotionsVM.loadFavouritesPotions()
        favouriteSpellsVM.loadFavouritesSpells()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(HogwartsBlue)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item { SectionHeader(title = "Libros") }

        items(items = favouritesBooks, key = { it.id }) { fav ->
            ItemFavouriteBook(
                book = fav,
                onDelete = { favouriteBooksVM.deleteFavouritesBooks(fav.id) }
            )
        }

        item { GoldDivider() }

        item { SectionHeader(title = "Películas") }

        items(items = favouritesMovies, key = { it.id }) { fav ->
            ItemFavouriteMovie(
                movie = fav,
                onDelete = { favouriteMoviesVM.deleteFavouritesMovies(fav.id) }
            )
        }

        item { GoldDivider() }

        item { SectionHeader(title = "Personajes") }

        items(items = favouritesCharacters, key = { it.id }) { fav ->
            ItemFavouriteCharacter(
                character = fav,
                onDelete = { favouriteCharactersVM.deleteFavouritesCharacter(fav.id) }
            )
        }

        item { GoldDivider() }

        item { SectionHeader(title = "Pociones") }

        items(items = favouritesPotions, key = { it.id }) { fav ->
            ItemFavouritePotion(
                potion = fav,
                onDelete = { favouritePotionsVM.deleteFavouritesPotion(fav.id) }
            )
        }

        item { GoldDivider() }

        item { SectionHeader(title = "Hechizos") }

        items(items = favouritesSpells, key = { it.id }) { fav ->
            ItemFavouriteSpell(
                spell = fav,
                onDelete = { favouriteSpellsVM.deleteFavouritesSpell(fav.id) }
            )
        }
    }
}



@Composable
fun SectionHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HogwartsGold, RoundedCornerShape(8.dp))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}

@Composable
fun GoldDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        thickness = 2.dp,
        color = HogwartsGold
    )
}


@Composable
fun ItemFavouriteBook(
    book: FavouriteBookDoc,
    onDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HogwartsGold, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {


            AsyncImage(
                model = book.data.image,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = book.data.name,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))


            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.Black
                )
            }

        }
    }
}


@Composable
fun ItemFavouriteMovie(
    movie: FavouriteMovieDoc,
    onDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HogwartsGold, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            AsyncImage(
                model = movie.data.image,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = movie.data.name,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.Black
                )
            }
        }
    }
}


@Composable
fun ItemFavouriteCharacter(
    character: FavouriteCharacterDoc,
    onDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HogwartsGold, RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = character.data.name,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun ItemFavouritePotion(
    potion: FavouritePotionDoc,
    onDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HogwartsGold, RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = potion.data.name,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun ItemFavouriteSpell(
    spell: FavouriteSpellDoc,
    onDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HogwartsGold, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = spell.data.name,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )


            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.Black
                )
            }
        }
    }
}