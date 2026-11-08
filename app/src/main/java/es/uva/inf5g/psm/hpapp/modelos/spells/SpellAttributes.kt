package es.uva.inf5g.psm.hpapp.modelos.spells
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class SpellAttributes (
    val category : String?,
    val creator : String?,
    val effect : String?,
    val hand : String?,
    val image : String?,
    val incantation : String?,
    val light : String?,
    val name : String?,
    val slug : String,
    val wiki : String?
) : Parcelable