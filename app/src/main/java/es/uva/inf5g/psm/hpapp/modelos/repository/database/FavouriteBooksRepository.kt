package es.uva.inf5g.psm.hpapp.modelos.repository.database

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import es.uva.inf5g.psm.hpapp.modelos.books.BookAttributes
import es.uva.inf5g.psm.hpapp.modelos.books.FavouriteBookDoc
import es.uva.inf5g.psm.hpapp.modelos.books.FavouriteBooks

object FavouriteBooksRepository {
    @SuppressLint("StaticFieldLeak")
    private val db = Firebase.firestore

    private val favsBooksCollection = db.collection("favsBooks")

    fun addFavouriteBook(
        book: BookAttributes,
        onResult: (Boolean, String?) -> Unit
    ) {
        favsBooksCollection
            .whereEqualTo("name", book.title)
            .get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.isEmpty) {
                    onResult(false, "Libro ya registrado")
                    return@addOnSuccessListener
                }

                val data = mapOf(
                    "name" to book.title,
                    "image" to book.cover,
                    "createdAt" to FieldValue.serverTimestamp()
                )

                favsBooksCollection.add(data)
                    .addOnSuccessListener { onResult(true, null) }
                    .addOnFailureListener { e -> onResult(false, e.message) }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }


    fun getFavouritesBooks(
        onResult: (List<FavouriteBookDoc>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        favsBooksCollection.get()
            .addOnSuccessListener { snapshot ->
                try {
                    val list = snapshot.documents.mapNotNull { doc ->
                        val fav = doc.toObject(FavouriteBooks::class.java)
                            ?: return@mapNotNull null
                        FavouriteBookDoc(doc.id, fav)
                    }
                    onResult(list)
                } catch (e: Exception) {
                    onError(e)
                }
            }
            .addOnFailureListener(onError)
    }

    fun deleteFavouriteBook(id: String, onResult: (Boolean, String?) -> Unit) {
        favsBooksCollection.document(id).delete()
            .addOnSuccessListener { onResult(true, null) }
            .addOnFailureListener { e -> onResult(false, e.message) }
    }

}
