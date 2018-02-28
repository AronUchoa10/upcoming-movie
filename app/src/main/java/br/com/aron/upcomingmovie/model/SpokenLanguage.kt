package br.com.aron.upcomingmovie.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Aron on 24/02/2018.
 */
class SpokenLanguage : Serializable {

    @SerializedName("iso_639_1")
    var iso6391: String? = null

    @SerializedName("name")
    var name: String? = null
}