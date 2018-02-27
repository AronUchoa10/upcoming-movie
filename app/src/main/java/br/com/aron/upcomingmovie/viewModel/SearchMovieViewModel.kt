package br.com.aron.upcomingmovie.viewModel

import android.content.Context
import br.com.aron.upcomingmovie.data.callbacks.GetGenreListResponse
import br.com.aron.upcomingmovie.data.webclients.GetGenreListWebClient
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.utils.Constants

/**
 * Created by Aron on 27/02/2018.
 */
abstract class SearchMovieViewModel {

//    private val genreList: MutableList<Genre>? = mutableListOf()
//
//    // GetGenreList
//    fun requestGenreList(context: Context): MutableList<Genre>? {
//
//        GetGenreListWebClient().getGenreList(context, Constants.apiKey, Constants.language,
//                object : GetGenreListResponse<List<Genre>> {
//                    override fun success(response: List<Genre>) {
//                        for (item in response) {
//                            this@SearchMovieViewModel.genreList!!.add(item)
//                        }
//                    }
//                })
//        return genreList
//    }
}