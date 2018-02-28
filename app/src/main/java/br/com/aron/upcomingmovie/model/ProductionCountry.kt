package br.com.aron.upcomingmovie.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Aron on 24/02/2018.
 */
class ProductionCountry : Serializable {

    @SerializedName("iso_3166_1")
    var iso31661: String? = null

    @SerializedName("name")
    var name: String? = null
}