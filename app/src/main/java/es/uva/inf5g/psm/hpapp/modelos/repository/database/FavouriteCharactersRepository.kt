package es.uva.inf5g.psm.hpapp.modelos.repository.database

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import es.uva.inf5g.psm.hpapp.modelos.characters.CharacterAttributes
import es.uva.inf5g.psm.hpapp.modelos.characters.FavouriteCharacterDoc
import es.uva.inf5g.psm.hpapp.modelos.characters.FavouriteCharacters



object FavouriteCharactersRepository {
    @SuppressLint("StaticFieldLeak")
    private val db = Firebase.firestore

    private val favsCharactersCollection = db.collection("favsCharacters")

    fun addFavouriteCharacter(
        character: CharacterAttributes,
        onResult: (Boolean, String?) -> Unit
    ) {
        favsCharactersCollection
            .whereEqualTo("name", character.name)
            .get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.isEmpty) {
                    onResult(false, "Personaje ya registrado")
                    return@addOnSuccessListener
                }

                val data = mapOf(
                    "name" to character.name,
                    "createdAt" to FieldValue.serverTimestamp()
                )

                favsCharactersCollection.add(data)
                    .addOnSuccessListener { onResult(true, null) }
                    .addOnFailureListener { e -> onResult(false, e.message) }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }


    fun getFavouritesCharacters(
        onResult: (List<FavouriteCharacterDoc>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        favsCharactersCollection.get()
            .addOnSuccessListener { snapshot ->
                try {
                    val list = snapshot.documents.mapNotNull { doc ->
                        val fav = doc.toObject(FavouriteCharacters::class.java)
                            ?: return@mapNotNull null
                        FavouriteCharacterDoc(doc.id, fav)
                    }
                    onResult(list)
                } catch (e: Exception) {
                    onError(e)
                }
            }
            .addOnFailureListener(onError)
    }

    fun deleteFavouriteCharacter(id: String, onResult: (Boolean, String?) -> Unit) {
        favsCharactersCollection.document(id).delete()
            .addOnSuccessListener { onResult(true, null) }
            .addOnFailureListener { e -> onResult(false, e.message) }
    }


}