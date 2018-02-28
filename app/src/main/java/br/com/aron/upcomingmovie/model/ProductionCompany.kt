package br.com.aron.upcomingmovie.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Aron on 24/02/2018.
 */
class ProductionCompany : Serializable {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null
}