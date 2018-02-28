package br.com.aron.upcomingmovie.data.services

import br.com.aron.upcomingmovie.model.UpcomingMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Aron on 26/02/2018.
 */
interface SearchMovieService {

    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey: String,
                    @Query("language") language: String,
                    @Query("query") query: String) : Call<UpcomingMovie>

    // This Querys can be util in future, but i will not use

//    @Query("page") page: Int?,
//    @Query("include_adult") includeAdult: Boolean?,
//    @Query("region") region: String?,
//    @Query("year") year: Int?,
//    @Query("primary_release_year") primaryReleaseYear: Int?
}