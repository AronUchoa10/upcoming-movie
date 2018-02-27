package br.com.aron.upcomingmovie.viewModel

import android.content.Context
import br.com.aron.upcomingmovie.data.callbacks.GetGenreListResponse
import br.com.aron.upcomingmovie.data.callbacks.GetUpcomingResponse
import br.com.aron.upcomingmovie.data.webclients.GetGenreListWebClient
import br.com.aron.upcomingmovie.data.webclients.GetUpcomingWebClient
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.model.MovieModel
import br.com.aron.upcomingmovie.utils.Constants

/**
 * Created by Aron on 25/02/2018.
 */
class MovieViewModel {

    // GetUpcomingMovie
    fun requestUpcomingMovie(numberPage: Int, context: Context, mServiceListener: MovieViewModelListener) {

        GetUpcomingWebClient().getUpcoming(context, Constants.apiKey, Constants.language,
                numberPage, "",object : GetUpcomingResponse<MutableList<MovieModel>> {
            override fun success(response: MutableList<MovieModel>) {
                mServiceListener.returnMovieListListener(response, numberPage)
            }
        })
    }

    // GetGenreList
    fun requestGenreList(context: Context, mServiceListener: MovieViewModelListener) {

        GetGenreListWebClient().getGenreList(context, Constants.apiKey, Constants.language,
                object : GetGenreListResponse<MutableList<Genre>> {
                    override fun success(response: MutableList<Genre>) {
                        mServiceListener.returnGenreListListener(response)
                    }
                })
    }

    interface MovieViewModelListener {

        fun returnMovieListListener(returnList: MutableList<MovieModel>, numberPage: Int)
        fun returnGenreListListener(returnList: MutableList<Genre>)
    }
}