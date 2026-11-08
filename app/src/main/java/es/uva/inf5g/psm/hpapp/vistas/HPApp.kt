package es.uva.inf5g.psm.hpapp.vistas

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.uva.inf5g.psm.hpapp.Screen
import es.uva.inf5g.psm.hpapp.modelos.books.BookAttributes
import es.uva.inf5g.psm.hpapp.modelos.characters.CharacterAttributes
import es.uva.inf5g.psm.hpapp.modelos.movies.MovieAttributes
import es.uva.inf5g.psm.hpapp.modelos.potions.PotionAttributes
import es.uva.inf5g.psm.hpapp.modelos.spells.SpellAttributes

@Composable
fun HPApp(paddingValues: PaddingValues) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.TitleScreen.ruta
    ) {

        composable(Screen.TitleScreen.ruta) {
            TitleScreen {
                navController.navigate(Screen.MainMenu.ruta)
            }
        }

        composable(Screen.MainMenu.ruta) {
            MainMenu( onNavigateToItemScreen = { item ->
                navController.navigate(
                    Screen.ItemsScreen.ruta.replace("{item}", item))},
                onNavigateToFavorites = {
                    navController.navigate(Screen.FavouritesScreen.ruta)
                }
                )

        }

        composable(Screen.ItemsScreen.ruta) { backStackEntry ->

            val item = backStackEntry.arguments?.getString("item") ?: ""

            ItemGUI(
                item = item,
                navigateToBookDetail = { book ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("book", book)
                    navController.navigate(Screen.BookDetailsScreen.ruta)
                },
                navigateToMovieDetail = { movie ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("movie", movie)
                    navController.navigate(Screen.MovieDetailsScreen.ruta)
                },
                navigateToCharacterDetail = { character ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("character", character)
                    navController.navigate(Screen.CharacterDetailsScreen.ruta)
                },
                navigateToPotionDetail = { potion ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("potion", potion)
                    navController.navigate(Screen.PotionDetailsScren.ruta)
                },
                navigateToSpellDetail = { spell ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("spell", spell)
                    navController.navigate(Screen.SpellDetailsScreen.ruta)
                }
            )
        }

        composable(Screen.BookDetailsScreen.ruta){ backStackEntry ->
            val book = navController.previousBackStackEntry?.savedStateHandle?.get<BookAttributes>("book") ?: BookAttributes(
                "",
                "",
                "",
                0,
                "",
                "",
                "",
                "",
                ""
            )
            BookDetailsScreen(book)
        }

        composable(Screen.MovieDetailsScreen.ruta){ backStackEntry ->
            val movie = navController.previousBackStackEntry?.savedStateHandle?.get<MovieAttributes>("movie")
                ?: MovieAttributes(
                    slug = "",
                    box_office = "",
                    budget = "",
                    cinematographers = emptyList(),
                    directors = emptyList(),
                    distributors = emptyList(),
                    editors = emptyList(),
                    music_composer = emptyList(),
                    poster = "",
                    producers = emptyList(),
                    rating = "",
                    release_date = null,
                    running_time = "",
                    screen_writers = emptyList(),
                    summary = "",
                    title = "",
                    trailer = "",
                    wiki = ""
                )

            MovieDetailsScreen(movie)
        }

        composable(Screen.CharacterDetailsScreen.ruta){ backStackEntry ->
            val character = navController.previousBackStackEntry?.savedStateHandle?.get<CharacterAttributes>("character")
                ?: CharacterAttributes(
                    alias_names = emptyList(),
                    animagus = "",
                    blood_status = "",
                    boggart = "",
                    born = "",
                    died = "",
                    eye_color = "",
                    family_member = emptyList(),
                    gender = "",
                    hair_color = "",
                    height = "",
                    house = "",
                    image = "",
                    jobs = emptyList(),
                    name = "",
                    nationality = "",
                    patronus = "",
                    romances = emptyList(),
                    skin_color = "",
                    slug = "",
                    species = "",
                    titles = emptyList(),
                    wand = emptyList(),
                    weight = "",
                    wiki = ""
                )

            CharacterDetailsScreen(character)
        }

        composable(Screen.PotionDetailsScren.ruta){ backStackEntry ->
            val potion = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<PotionAttributes>("potion")
                ?: PotionAttributes(
                    slug = "",
                    characteristics = "",
                    difficulty = "",
                    effect = "",
                    image = "",
                    inventors = "",
                    ingredients = "",
                    manufactures = "",
                    name = "",
                    side_effects = "",
                    time = "",
                    wiki = ""
                )

            PotionDetailsScreen(potion)
        }

        composable(Screen.SpellDetailsScreen.ruta){ backStackEntry ->
            val spell = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<SpellAttributes>("spell")
                ?: SpellAttributes(
                    category = "",
                    creator = "",
                    effect = "",
                    hand = "",
                    image = "",
                    incantation = "",
                    light = "",
                    name = "",
                    slug = "",
                    wiki = ""
                )

            SpellDetailsScreen(spell)
        }

        composable(Screen.FavouritesScreen.ruta){
            FavouritesScreen()
        }
    }
}
