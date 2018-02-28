package br.com.aron.upcomingmovie.data.services

import br.com.aron.upcomingmovie.model.MovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Aron on 26/02/2018.
 */
interface GetDetailService {

    @GET("movie/{movie_id}")
    fun getDetails(@Path("movie_id") movieID: Int,
                   @Query("api_key") apiKey: String,
                   @Query("language") language: String,
                   @Query("append_to_response") appendToResponse: String ): Call<MovieDetail>
}