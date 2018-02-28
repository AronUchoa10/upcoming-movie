package br.com.aron.upcomingmovie.data.services

import br.com.aron.upcomingmovie.model.UpcomingMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Aron on 23/02/2018.
 */
interface GetUpcomingService {

    @GET("movie/upcoming")
    fun getUpcoming(@Query("api_key") apiKey: String,
                    @Query("language") language: String,
                    @Query("page") page: Int,
                    @Query("region") region: String) : Call<UpcomingMovie>
}