package es.uva.inf5g.psm.hpapp.modelos.potions
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class PotionAttributes(
    val slug : String,
    val characteristics : String?,
    val difficulty : String?,
    val effect : String?,
    val image : String?,
    val inventors : String?,
    val ingredients : String?,
    val manufactures : String?,
    val name : String?,
    val side_effects : String?,
    val time : String?,
    val wiki : String?
) : Parcelable