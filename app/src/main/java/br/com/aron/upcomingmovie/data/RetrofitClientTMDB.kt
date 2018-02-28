package br.com.aron.upcomingmovie.data

import br.com.aron.upcomingmovie.data.services.GetDetailService
import br.com.aron.upcomingmovie.data.services.GetGenreListService
import br.com.aron.upcomingmovie.data.services.GetUpcomingService
import br.com.aron.upcomingmovie.data.services.SearchMovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Aron on 23/02/2018.
 */
class RetrofitClientTMDB {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getGenreListService() = retrofit.create(GetGenreListService::class.java)

    fun getUpcomingService() = retrofit.create(GetUpcomingService::class.java)

    fun getDetailService() = retrofit.create(GetDetailService::class.java)

    fun searchMovieService() = retrofit.create(SearchMovieService::class.java)
}