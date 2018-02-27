package br.com.aron.upcomingmovie.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Aron on 24/02/2018.
 */
class GenreList {

    @SerializedName("genres")
    var genres: List<Genre>? = null
}