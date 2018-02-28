package br.com.aron.upcomingmovie.viewModel

import android.content.Context
import br.com.aron.upcomingmovie.data.callbacks.GetGenreListResponse
import br.com.aron.upcomingmovie.data.callbacks.SearchMovieResponse
import br.com.aron.upcomingmovie.data.webclients.GetGenreListWebClient
import br.com.aron.upcomingmovie.data.webclients.SearchMovieWebClient
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.model.MovieModel
import br.com.aron.upcomingmovie.utils.Constants
import java.util.*

/**
 * Created by Aron on 27/02/2018.
 */
class SearchMovieViewModel {

    var language = Locale.getDefault().toString()

    // GetGenreList
    fun requestGenreList(context: Context, mServiceListener: SearchMovieViewModelListener)  {

        GetGenreListWebClient().getGenreList(context, Constants.apiKey, language,
                object : GetGenreListResponse<MutableList<Genre>> {
                    override fun success(response: MutableList<Genre>) {
                        mServiceListener.returnGenreListListener(response)
                    }
                })
    }

    // SearchMovie
    fun requestSearchMovie(context: Context, query: String, mServiceListener: SearchMovieViewModelListener){

        SearchMovieWebClient().searchMovie(context, Constants.apiKey, language, query,
                object: SearchMovieResponse<MutableList<MovieModel>> {
                    override fun success(response: MutableList<MovieModel>) {
                        mServiceListener.returnMovieListListener(response)
                    }
                })
    }

    interface SearchMovieViewModelListener {

        fun returnMovieListListener(returnList: MutableList<MovieModel>)
        fun returnGenreListListener(returnList: MutableList<Genre>)
    }
}