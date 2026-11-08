package es.uva.inf5g.psm.hpapp.modelos.chapters
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class ChapterAttributes (
    val slug : String,
    val order : Integer,
    val summary : String?,
    val title : String
) : Parcelable