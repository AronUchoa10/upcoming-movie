package br.com.aron.upcomingmovie.data.webclients

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import br.com.aron.upcomingmovie.R
import br.com.aron.upcomingmovie.data.RetrofitClientTMDB
import br.com.aron.upcomingmovie.data.callbacks.SearchMovieResponse
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.model.MovieModel
import br.com.aron.upcomingmovie.model.UpcomingMovie
import br.com.aron.upcomingmovie.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Aron on 26/02/2018.
 */
class SearchMovieWebClient {

    fun searchMovie(context: Context, apiKey: String, language: String, query: String,
                    callbackResponse: SearchMovieResponse<MutableList<MovieModel>>){

        val call = RetrofitClientTMDB().searchMovieService().searchMovie(apiKey, language, query)
        call.enqueue(object: Callback<UpcomingMovie> {
            override fun onResponse(call: Call<UpcomingMovie?>?,
                                    response: Response<UpcomingMovie?>?) {

                Log.d("sucesso", "sucesso")
                response?.body().let {

                    val upcomingMovie: UpcomingMovie = it!!
                    val results = upcomingMovie.results
                    callbackResponse.success(results!!)
                }
            }

            override fun onFailure(call: Call<UpcomingMovie?>?,
                                   t: Throwable?) {

                Log.e("onFailure error", t?.message)
                Utils().showDialogWithoutCancel(context,context.getString(R.string.error),
                        context.getString(R.string.message_error),DialogInterface.OnClickListener { dialog, i -> dialog.dismiss() })
            }
        })
    }
}