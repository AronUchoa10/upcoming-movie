package br.com.aron.upcomingmovie.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Aron on 24/02/2018.
 */
class UpcomingMovie : Serializable {

    var results: ArrayList<MovieModel>? = null

    var page: Int? = null

    @SerializedName("total_results")
    var totalResults: Int? = null

    var dates: UpcomingDates? = null

    @SerializedName("total_pages")
    var totalPages: String? = null
}