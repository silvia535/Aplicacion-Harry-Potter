package es.uva.inf5g.psm.hpapp

sealed class Screen(val ruta: String) {
    data object TitleScreen : Screen("title_screen")
    data object MainMenu : Screen("main_menu")
    data object ItemsScreen : Screen("item_screen/{item}")
    data object BookDetailsScreen : Screen("book_detail_screen")
    data object MovieDetailsScreen : Screen ("movie_detail_screen")
    data object CharacterDetailsScreen : Screen ("character_detail_screen")
    data object PotionDetailsScren : Screen ("potion_detail_screen")
    data object SpellDetailsScreen : Screen ("spell_detail_screen")
    data object FavouritesScreen : Screen ("favourites_screen")
}
