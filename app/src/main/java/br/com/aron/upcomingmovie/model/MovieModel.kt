package br.com.aron.upcomingmovie.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Aron on 24/02/2018.
 */
class MovieModel(i: Int, i1: Int, b: Boolean, d: Double, s: String, s1: String, s2: String, ints: Array<Int>, s3: String, b1: Boolean, s4: String) : Serializable {

    @SerializedName("vote_count")
    var voteCount: Int? = null

    var id: Int? = null

    var video: Boolean? = null

    @SerializedName("vote_average")
    var voteAverage: Double? = null

    var title: String? = null

    var popularity: Double? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    var adult: Boolean? = null

    var overview: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null
}