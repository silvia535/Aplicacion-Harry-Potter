package es.uva.inf5g.psm.hpapp

import es.uva.inf5g.psm.hpapp.modelos.books.BooksResponse
import es.uva.inf5g.psm.hpapp.modelos.characters.CharactersResponse
import es.uva.inf5g.psm.hpapp.modelos.movies.MoviesResponse
import es.uva.inf5g.psm.hpapp.modelos.potions.PotionsResponse
import es.uva.inf5g.psm.hpapp.modelos.spells.SpellsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.potterdb.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val itemService: APIService = retrofit.create(APIService::class.java)


interface APIService {
    @GET("books")
    suspend fun getBooks(): BooksResponse

    @GET("movies")
    suspend fun getMovies(): MoviesResponse

    @GET("characters")
    suspend fun getCharacters(
        @Query("page[number]") page: Int,
        @Query("page[size]") size: Int,
        @Query("filter[name_cont]") name: String? = null
    ): CharactersResponse

    @GET("potions")
    suspend fun getPotions(
        @Query("page[number]") page : Int,
        @Query("page[size]") size : Int,
        @Query("filter[name_cont]") name: String? = null
    ): PotionsResponse

    @GET("spells")
    suspend fun getSpells(
        @Query("page[number]") page : Int,
        @Query("page[size]") size : Int,
        @Query("filter[name_cont]") name: String? = null
    ): SpellsResponse

}
