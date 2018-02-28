package br.com.aron.upcomingmovie.data.services

import br.com.aron.upcomingmovie.model.GenreList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Aron on 25/02/2018.
 */
interface GetGenreListService {

    @GET("genre/movie/list")
    fun getGenreList(@Query("api_key") apiKey: String,
                     @Query("language") language: String) : Call<GenreList>
}