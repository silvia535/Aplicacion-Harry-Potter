package es.uva.inf5g.psm.hpapp.modelos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    val self: String
) : Parcelable