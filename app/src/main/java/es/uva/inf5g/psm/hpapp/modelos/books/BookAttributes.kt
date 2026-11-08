package es.uva.inf5g.psm.hpapp.modelos.books

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class BookAttributes(
    val slug : String,
    val title: String,
    val summary: String,
    val pages: Int?,
    val dedication: String?,
    val release_date: String?,
    val cover: String?,
    val author: String?,
    val wiki: String?
) : Parcelable