package es.uva.inf5g.psm.hpapp.modelos.movies

import android.os.Parcelable
import java.util.Date
import kotlinx.parcelize.Parcelize
@Parcelize
data class MovieAttributes(
    val slug: String,
    val box_office: String?,
    val budget: String?,
    val cinematographers: List<String>?,
    val directors: List<String>,
    val distributors: List<String>?,
    val editors: List<String>?,
    val music_composer: List<String>?,
    val poster: String?,
    val producers: List<String>?,
    val rating: String?,
    val release_date: Date?,
    val running_time: String?,
    val screen_writers: List<String>?,
    val summary: String,
    val title: String?,
    val trailer: String?,
    val wiki: String?
) : Parcelable