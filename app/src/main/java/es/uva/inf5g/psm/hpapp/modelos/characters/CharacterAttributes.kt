package es.uva.inf5g.psm.hpapp.modelos.characters
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class CharacterAttributes(
    val alias_names: List<String>?,
    val animagus: String?,
    val blood_status: String?,
    val boggart: String?,
    val born: String?,
    val died: String?,
    val eye_color: String?,
    val family_member: List<String>?,
    val gender: String?,
    val hair_color: String?,
    val height: String?,
    val house: String?,
    val image: String?,
    val jobs: List<String>?,
    val name: String?,
    val nationality: String?,
    val patronus: String?,
    val romances: List<String>?,
    val skin_color: String?,
    val slug: String,
    val species: String?,
    val titles: List<String>?,
    val wand: List<String>?,
    val weight: String?,
    val wiki: String?
) : Parcelable
