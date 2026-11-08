package es.uva.inf5g.psm.hpapp.modelos.repository.database

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import es.uva.inf5g.psm.hpapp.modelos.potions.FavouritePotionDoc
import es.uva.inf5g.psm.hpapp.modelos.potions.FavouritePotions
import es.uva.inf5g.psm.hpapp.modelos.potions.PotionAttributes

object FavouritePotionsRepository {
    @SuppressLint("StaticFieldLeak")
    private val db = Firebase.firestore

    private val favsPotionsCollection = db.collection("favsPotions")

    fun addFavouritePotion(
        potion: PotionAttributes,
        onResult: (Boolean, String?) -> Unit
    ) {
        favsPotionsCollection
            .whereEqualTo("name", potion.name)
            .get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.isEmpty) {
                    onResult(false, "Poción ya registrada")
                    return@addOnSuccessListener
                }

                val data = mapOf(
                    "name" to potion.name,
                    "createdAt" to FieldValue.serverTimestamp()
                )

                favsPotionsCollection.add(data)
                    .addOnSuccessListener { onResult(true, null) }
                    .addOnFailureListener { e -> onResult(false, e.message) }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }


    fun getFavouritesPotions(
        onResult: (List<FavouritePotionDoc>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        favsPotionsCollection.get()
            .addOnSuccessListener { snapshot ->
                try {
                    val list = snapshot.documents.mapNotNull { doc ->
                        val fav = doc.toObject(FavouritePotions::class.java)
                            ?: return@mapNotNull null
                        FavouritePotionDoc(doc.id, fav)
                    }
                    onResult(list)
                } catch (e: Exception) {
                    onError(e)
                }
            }
            .addOnFailureListener(onError)
    }

    fun deleteFavouritePotion(id: String, onResult: (Boolean, String?) -> Unit) {
        favsPotionsCollection.document(id).delete()
            .addOnSuccessListener { onResult(true, null) }
            .addOnFailureListener { e -> onResult(false, e.message) }
    }
}