package es.uva.inf5g.psm.hpapp.vistas


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Icon
import es.uva.inf5g.psm.hpapp.*
import es.uva.inf5g.psm.hpapp.modelos.books.BookAttributes
import es.uva.inf5g.psm.hpapp.modelos.books.BookData
import es.uva.inf5g.psm.hpapp.modelos.books.FavouriteBookDoc
import es.uva.inf5g.psm.hpapp.viewmodels.factory.BookViewModelFactory
import es.uva.inf5g.psm.hpapp.modelos.repository.api.BooksRepository
import es.uva.inf5g.psm.hpapp.modelos.characters.CharacterAttributes
import es.uva.inf5g.psm.hpapp.modelos.characters.FavouriteCharacterDoc
import es.uva.inf5g.psm.hpapp.viewmodels.factory.CharacterViewModelFactory
import es.uva.inf5g.psm.hpapp.modelos.repository.api.CharactersRepository
import es.uva.inf5g.psm.hpapp.modelos.estadospeticiones.CharactersFetchState
import es.uva.inf5g.psm.hpapp.modelos.estadospeticiones.PotionsFetchState
import es.uva.inf5g.psm.hpapp.modelos.estadospeticiones.SpellsFetchState
import es.uva.inf5g.psm.hpapp.modelos.movies.FavouriteMovieDoc
import es.uva.inf5g.psm.hpapp.modelos.movies.MovieAttributes
import es.uva.inf5g.psm.hpapp.modelos.movies.MovieData
import es.uva.inf5g.psm.hpapp.modelos.potions.FavouritePotionDoc
import es.uva.inf5g.psm.hpapp.viewmodels.factory.MovieViewModelFactory
import es.uva.inf5g.psm.hpapp.modelos.repository.api.MoviesRepository
import es.uva.inf5g.psm.hpapp.modelos.potions.PotionAttributes
import es.uva.inf5g.psm.hpapp.viewmodels.factory.PotionViewModelFactory
import es.uva.inf5g.psm.hpapp.modelos.repository.api.PotionsRepository
import es.uva.inf5g.psm.hpapp.modelos.spells.SpellAttributes
import es.uva.inf5g.psm.hpapp.viewmodels.factory.SpellViewModelFactory
import es.uva.inf5g.psm.hpapp.modelos.repository.api.SpellsRepository
import es.uva.inf5g.psm.hpapp.modelos.spells.FavouriteSpellDoc
import es.uva.inf5g.psm.hpapp.viewmodels.api.BookViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.api.CharacterViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.api.MovieViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.api.PotionViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.api.SpellViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouriteBookViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouriteCharacterViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouriteMovieViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouritePotionViewModel
import es.uva.inf5g.psm.hpapp.viewmodels.database.FavouriteSpellViewModel


@Composable
fun ItemGUI(item: String,
            navigateToBookDetail: (BookAttributes) -> Unit,
            navigateToMovieDetail : (MovieAttributes) -> Unit,
            navigateToCharacterDetail : (CharacterAttributes) -> Unit,
            navigateToPotionDetail : (PotionAttributes) -> Unit,
            navigateToSpellDetail : (SpellAttributes) -> Unit
) {
    if (item == "Ninguno") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(HogwartsBlue),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Pantalla de en construcción",
                color = Color.White
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HogwartsBlue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HPLogo()

            Box(modifier = Modifier.fillMaxSize()) {
                when (item) {
                    "Libros" -> {
                        val booksRepository = remember { BooksRepository(itemService) }
                        val bookViewModel: BookViewModel = viewModel(
                            factory = BookViewModelFactory(booksRepository)
                        )

                        val bookViewState by bookViewModel.booksState
                        when {
                            bookViewState.loading -> CenterLoading()
                            bookViewState.error != null -> CenterError(bookViewState.error!!)
                            else -> BooksScreen(
                                bookData = bookViewState.bookData,
                                navigateToBookDetail = navigateToBookDetail
                            )
                        }
                    }


                    "Películas" -> {
                        val moviesRepository = remember { MoviesRepository(itemService) }
                        val movieViewModel: MovieViewModel = viewModel(
                            factory = MovieViewModelFactory(moviesRepository)
                        )
                        val movieViewState by movieViewModel.moviesState
                        when {
                            movieViewState.loading -> CenterLoading()
                            movieViewState.error != null -> CenterError(movieViewState.error!!)
                            else -> MoviesScreen(movieViewState.movieData,
                                navigateToMovieDetail = navigateToMovieDetail)
                        }
                    }

                    "Personajes" -> {
                        val repository = remember { CharactersRepository(itemService) }
                        val characterViewModel: CharacterViewModel = viewModel(
                            factory = CharacterViewModelFactory(repository)
                        )
                        val characterViewState by characterViewModel.charactersState
                        when {
                            characterViewState.loading -> CenterLoading()
                            characterViewState.error != null -> CenterError(characterViewState.error!!)
                            else -> CharactersScreen(
                                state = characterViewState,
                                viewModel = characterViewModel,
                                navigateToCharacterDetail = navigateToCharacterDetail
                            )
                        }
                    }

                    "Pociones" -> {
                        val repository = remember { PotionsRepository(itemService) }
                        val potionViewModel: PotionViewModel = viewModel(
                            factory = PotionViewModelFactory(repository)
                        )
                        val potionViewState by potionViewModel.potionsState
                        when {
                            potionViewState.loading -> CenterLoading()
                            potionViewState.error != null -> CenterError(potionViewState.error!!)
                            else -> PotionsScreen(potionViewState, potionViewModel,
                                navigateToPotionDetail = navigateToPotionDetail)
                        }
                    }

                    "Hechizos" -> {
                        val repository = remember { SpellsRepository(itemService) }
                        val spellViewModel: SpellViewModel = viewModel(
                            factory = SpellViewModelFactory(repository)
                        )
                        val spellViewState by spellViewModel.spellsState
                        when {
                            spellViewState.loading -> CenterLoading()
                            spellViewState.error != null -> CenterError(spellViewState.error!!)
                            else -> SpellsScreen(spellViewState, spellViewModel,
                                navigateToSpellDetail = navigateToSpellDetail)
                        }
                    }
                }
            }
        }
    }
}


/* ---------- LIBROS ---------- */

@Composable
fun BooksScreen(
    bookData: List<BookData>,
    navigateToBookDetail: (BookAttributes) -> Unit
) {
    val favouritesBooksVM: FavouriteBookViewModel = viewModel()

    // Cargar favoritos una sola vez
    LaunchedEffect(Unit) {
        favouritesBooksVM.loadFavouritesBooks()
    }

    val favourites by favouritesBooksVM.favourites.collectAsState()

    TwoColumnGrid {
        items(bookData) { book ->
            BookItem(
                book = book.attributes,
                navigateToBookDetail = navigateToBookDetail,
                favouritesBooksVM = favouritesBooksVM,
                favourites = favourites
            )
        }
    }
}



/* ---------- PELÍCULAS ---------- */

@Composable
fun MoviesScreen(movieData: List<MovieData>,
                 navigateToMovieDetail: (MovieAttributes) -> Unit) {

    val favouritesMoviesVM: FavouriteMovieViewModel = viewModel()
    val favourites by favouritesMoviesVM.favourites.collectAsState()

    // Cargar favoritos una sola vez
    LaunchedEffect(Unit) {
        favouritesMoviesVM.loadFavouritesMovies()
    }



    TwoColumnGrid {
        items(movieData) { movie ->
            MovieItem(movie = movie.attributes, navigateToMovieDetail = navigateToMovieDetail, favouritesMoviesVM = favouritesMoviesVM, favourites = favourites)
        }
    }
}

/* ---------- PERSONAJES ---------- */

@Composable
fun CharactersScreen(
    state: CharactersFetchState,
    viewModel: CharacterViewModel,
    modifier: Modifier = Modifier,
    navigateToCharacterDetail : (CharacterAttributes) -> Unit
) {
    val gridState = rememberLazyGridState()
    val favouritesCharactersVM : FavouriteCharacterViewModel = viewModel()

    LaunchedEffect(state.currentPage) {
        gridState.scrollToItem(0)
    }

    LaunchedEffect(Unit) {
        favouritesCharactersVM.loadFavouritesCharacters()
    }

    val favourites by favouritesCharactersVM.favourites.collectAsState()


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(HogwartsBlue)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                modifier = Modifier.weight(1f),
                query = state.searchQuery,
                onQueryChange = viewModel::updateCharacterSearch,
                item = "personaje"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = viewModel::searchCharacters,
                colors = ButtonDefaults.buttonColors(
                    containerColor = HogwartsGold,
                    contentColor = Color.Black
                )
            ) {
                Text("Buscar")
            }

        }

        TwoColumnGrid(
            modifier = Modifier.weight(1f),
            state = gridState
        ) {
            items(state.characters) { character ->
                CharacterItem(character = character.attributes, navigateToCharacterDetail = navigateToCharacterDetail,favouritesCharactersVM = favouritesCharactersVM, favourites = favourites )
            }
        }

        PaginationControls(
            currentPage = state.currentPage,
            hasPrev = state.hasPrevPage,
            hasNext = state.hasNextPage,
            onPrev = viewModel::prevCharactersPage,
            onNext = viewModel::nextCharactersPage
        )
    }
}

/* ---------- POCIONES ---------- */
@Composable
fun PotionsScreen(
    state: PotionsFetchState,
    viewModel: PotionViewModel,
    modifier: Modifier = Modifier,
    navigateToPotionDetail : (PotionAttributes) -> Unit
) {
    val gridState = rememberLazyGridState()
    val favouritesPotionsVM: FavouritePotionViewModel = viewModel()
    val favourites by favouritesPotionsVM.favourites.collectAsState()


    LaunchedEffect(state.currentPage) {
        gridState.scrollToItem(0)
    }

    LaunchedEffect(Unit) {
        favouritesPotionsVM.loadFavouritesPotions()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(HogwartsBlue)
    ) {

        // 🔍 Búsqueda con botón
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                modifier = Modifier.weight(1f),
                query = state.searchQuery,
                onQueryChange = viewModel::updatePotionSearch,
                item = "poción"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = viewModel::searchPotions,
                colors = ButtonDefaults.buttonColors(
                    containerColor = HogwartsGold,
                    contentColor = Color.Black
                )
            ) {
                Text("Buscar")
            }

        }

        TwoColumnGrid(
            modifier = Modifier.weight(1f),
            state = gridState
        ) {
            items(state.potions) { potion ->
                PotionItem(potion = potion.attributes, navigateToPotionDetail = navigateToPotionDetail, favouritesPotionsVM = favouritesPotionsVM, favourites = favourites)
            }
        }

        PaginationControls(
            currentPage = state.currentPage,
            hasPrev = state.hasPrevPage,
            hasNext = state.hasNextPage,
            onPrev = viewModel::prevPotionsPage,
            onNext = viewModel::nextPotionsPage
        )
    }
}
/* ---------- HECHIZOS ---------- */

@Composable
fun SpellsScreen(
    state: SpellsFetchState,
    viewModel: SpellViewModel,
    modifier: Modifier = Modifier,
    navigateToSpellDetail : (SpellAttributes) -> Unit
) {
    val gridState = rememberLazyGridState()
    val favouritesSpellsVM : FavouriteSpellViewModel = viewModel()
    val favourites by favouritesSpellsVM.favourites.collectAsState()

    LaunchedEffect(state.currentPage) {
        gridState.scrollToItem(0)
    }

    LaunchedEffect(Unit) {
        favouritesSpellsVM.loadFavouritesSpells()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(HogwartsBlue)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                modifier = Modifier.weight(1f),
                query = state.searchQuery,
                onQueryChange = viewModel::updateSpellsSearch,
                item = "hechizo"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = viewModel::searchSpells,
                colors = ButtonDefaults.buttonColors(
                    containerColor = HogwartsGold,
                    contentColor = Color.Black
                )
            ) {
                Text("Buscar")
            }

        }

        TwoColumnGrid(
            modifier = Modifier.weight(1f),
            state = gridState
        ) {
            items(state.spells) { spell ->
                SpellItem(spell = spell.attributes, navigateToSpellDetail = navigateToSpellDetail, favouritesSpellsVM = favouritesSpellsVM, favourites = favourites)
            }
        }

        PaginationControls(
            currentPage = state.currentPage,
            hasPrev = state.hasPrevPage,
            hasNext = state.hasNextPage,
            onPrev = viewModel::prevSpellsPage,
            onNext = viewModel::nextSpellsPage
        )
    }
}

/* ---------- ITEMS ---------- */

@Composable
fun BookItem(
    book: BookAttributes,
    navigateToBookDetail: (BookAttributes) -> Unit,
    favouritesBooksVM: FavouriteBookViewModel,
    favourites: List<FavouriteBookDoc>
) {


    val isFavourite = remember(book.title, favourites) {
        favourites.any { it.data.name == book.title }
    }


    Box {
        MediaCard(
            imageUrl = book.cover,
            title = book.title,
            item = "Libro",
            modifier = Modifier.clickable { navigateToBookDetail(book) }
        )

        IconButton(
            onClick = {
                if (isFavourite) {
                    val favBook = favourites.find { it.data.name == book.title }
                    favBook?.let { favouritesBooksVM.deleteFavouritesBooks(it.id) }
                } else {
                    favouritesBooksVM.addFavouriteBook(book)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .offset(x = 6.dp, y = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Favorito",
                tint = if (isFavourite) Color.Yellow else Color.Black
            )
        }
    }
}






@Composable
fun MovieItem(movie: MovieAttributes,
              navigateToMovieDetail: (MovieAttributes) -> Unit,
              favouritesMoviesVM : FavouriteMovieViewModel,
              favourites: List<FavouriteMovieDoc>
) {
    val isFavourite = remember(movie.title, favourites){
       favourites.any{it.data.name == movie.title}
    }


    Box{
        MediaCard(
            movie.poster,
            movie.title ?: "Sin título",
            "Película",
            modifier = Modifier.clickable { navigateToMovieDetail(movie) }
        )
        IconButton(
            onClick = {
                if (isFavourite) {
                    val favMovie = favourites.find { it.data.name == movie.title }
                    favMovie?.let { favouritesMoviesVM.deleteFavouritesMovies(it.id) }
                } else {
                    favouritesMoviesVM.addFavouriteMovie(movie)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .offset(x = 6.dp, y = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Favorito",
                tint = if (isFavourite) Color.Yellow else Color.Black
            )
        }
    }
}

@Composable
fun CharacterItem(character: CharacterAttributes,
                  navigateToCharacterDetail : (CharacterAttributes) -> Unit,
                  favouritesCharactersVM: FavouriteCharacterViewModel,
                  favourites: List<FavouriteCharacterDoc>) {


    val isFavourite = remember(character.name, favourites){
        favourites.any{it.data.name == character.name}
    }


    Box{
        MediaCard(
            character.image,
            character.name ?: "Sin nombre",
            "Personaje",
            modifier = Modifier.clickable { navigateToCharacterDetail(character) }
        )

        IconButton(
            onClick = {
                if (isFavourite) {
                    val favCharacter = favourites.find { it.data.name == character.name }
                    favCharacter?.let { favouritesCharactersVM.deleteFavouritesCharacter(it.id) }
                } else {
                    favouritesCharactersVM.addFavouriteCharacter(character)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .offset(x = 6.dp, y = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Favorito",
                tint = if (isFavourite) Color.Yellow else Color.Black
            )
        }
    }

}

@Composable
fun PotionItem(potion: PotionAttributes,
               navigateToPotionDetail : (PotionAttributes) -> Unit,
               favouritesPotionsVM: FavouritePotionViewModel,
               favourites: List<FavouritePotionDoc>
) {

    val isFavourite = remember(potion.name, favourites){
        favourites.any { it.data.name == potion.name }
    }
    Box{
        MediaCard(potion.image, potion.name ?:"Unknown", "Poción", modifier = Modifier.clickable { navigateToPotionDetail(potion) })
        IconButton(
            onClick = {
                if (isFavourite) {
                    val favPotion = favourites.find { it.data.name == potion.name }
                    favPotion?.let { favouritesPotionsVM.deleteFavouritesPotion(it.id)}
                } else {
                    favouritesPotionsVM.addFavouritePotion(potion)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .offset(x = 6.dp, y = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Favorito",
                tint = if (isFavourite) Color.Yellow else Color.Black
            )
        }
    }

}

@Composable
fun SpellItem(spell: SpellAttributes,
              navigateToSpellDetail : (SpellAttributes) -> Unit,
              favouritesSpellsVM: FavouriteSpellViewModel,
              favourites: List<FavouriteSpellDoc>
) {

    val isFavourite = remember ( spell.name, favourites ){
        favourites.any { it.data.name  == spell.name}
    }
    Box{
        MediaCard(spell.image, spell.name ?: "Unknown", "Hechizo", modifier = Modifier.clickable { navigateToSpellDetail(spell) })
        IconButton(
            onClick = {
                if (isFavourite) {
                    val favSpell = favourites.find { it.data.name == spell.name }
                    favSpell?.let { favouritesSpellsVM.deleteFavouritesSpell(it.id) }
                } else {
                    favouritesSpellsVM.addFavouriteSpell(spell)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .offset(x = 6.dp, y = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Favorito",
                tint = if (isFavourite) Color.Yellow else Color.Black
            )
        }
    }


}
