package br.com.aron.upcomingmovie.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Aron on 24/02/2018.
 */
class ProductionCompany {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null
}