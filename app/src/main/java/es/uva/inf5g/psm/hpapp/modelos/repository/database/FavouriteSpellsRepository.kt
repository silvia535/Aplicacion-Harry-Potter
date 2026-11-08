package es.uva.inf5g.psm.hpapp.modelos.repository.database

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import es.uva.inf5g.psm.hpapp.modelos.spells.FavouriteSpellDoc
import es.uva.inf5g.psm.hpapp.modelos.spells.FavouriteSpells
import es.uva.inf5g.psm.hpapp.modelos.spells.SpellAttributes

object FavouriteSpellsRepository {
    @SuppressLint("StaticFieldLeak")
    private val db = Firebase.firestore

    private val favsSpellsCollection = db.collection("favsSpells")

    fun addFavouriteSpell(
        spell: SpellAttributes,
        onResult: (Boolean, String?) -> Unit
    ) {
        favsSpellsCollection
            .whereEqualTo("name", spell.name)
            .get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.isEmpty) {
                    onResult(false, "Hechizo ya registrado")
                    return@addOnSuccessListener
                }

                val data = mapOf(
                    "name" to spell.name,
                    "createdAt" to FieldValue.serverTimestamp()
                )

                favsSpellsCollection.add(data)
                    .addOnSuccessListener { onResult(true, null) }
                    .addOnFailureListener { e -> onResult(false, e.message) }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }


    fun getFavouritesSpells(
        onResult: (List<FavouriteSpellDoc>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        favsSpellsCollection.get()
            .addOnSuccessListener { snapshot ->
                try {
                    val list = snapshot.documents.mapNotNull { doc ->
                        val fav = doc.toObject(FavouriteSpells::class.java)
                            ?: return@mapNotNull null
                        FavouriteSpellDoc(doc.id, fav)
                    }
                    onResult(list)
                } catch (e: Exception) {
                    onError(e)
                }
            }
            .addOnFailureListener(onError)
    }

    fun deleteFavouriteSpell(id: String, onResult: (Boolean, String?) -> Unit) {
        favsSpellsCollection.document(id).delete()
            .addOnSuccessListener { onResult(true, null) }
            .addOnFailureListener { e -> onResult(false, e.message) }
    }
}