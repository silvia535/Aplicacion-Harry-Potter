package es.uva.inf5g.psm.hpapp.modelos.repository.database

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import es.uva.inf5g.psm.hpapp.modelos.movies.FavouriteMovieDoc
import es.uva.inf5g.psm.hpapp.modelos.movies.FavouriteMovies
import es.uva.inf5g.psm.hpapp.modelos.movies.MovieAttributes


object FavouriteMoviesRepository {
    @SuppressLint("StaticFieldLeak")
    private val db = Firebase.firestore

    private val favsMoviesCollection = db.collection("favsMovies")

    fun addFavouriteMovie(
        movie: MovieAttributes,
        onResult: (Boolean, String?) -> Unit
    ) {
        favsMoviesCollection
            .whereEqualTo("name", movie.title)
            .get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.isEmpty) {
                    onResult(false, "Película ya registrada")
                    return@addOnSuccessListener
                }

                val data = mapOf(
                    "name" to movie.title,
                    "image" to movie.poster,
                    "createdAt" to FieldValue.serverTimestamp()
                )

                favsMoviesCollection.add(data)
                    .addOnSuccessListener { onResult(true, null) }
                    .addOnFailureListener { e -> onResult(false, e.message) }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }


    fun getFavouritesMovies(
        onResult: (List<FavouriteMovieDoc>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        favsMoviesCollection.get()
            .addOnSuccessListener { snapshot ->
                try {
                    val list = snapshot.documents.mapNotNull { doc ->
                        val fav = doc.toObject(FavouriteMovies::class.java)
                            ?: return@mapNotNull null
                        FavouriteMovieDoc(doc.id, fav)
                    }
                    onResult(list)
                } catch (e: Exception) {
                    onError(e)
                }
            }
            .addOnFailureListener(onError)
    }

    fun deleteFavouriteMovie(id: String, onResult: (Boolean, String?) -> Unit) {
        favsMoviesCollection.document(id).delete()
            .addOnSuccessListener { onResult(true, null) }
            .addOnFailureListener { e -> onResult(false, e.message) }
    }

}