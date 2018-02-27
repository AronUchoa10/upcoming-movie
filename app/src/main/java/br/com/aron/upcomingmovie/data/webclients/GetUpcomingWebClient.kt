package br.com.aron.upcomingmovie.data.webclients

import android.content.Context
import android.util.Log
import br.com.aron.upcomingmovie.data.RetrofitClientTMDB
import br.com.aron.upcomingmovie.data.callbacks.GetUpcomingResponse
import br.com.aron.upcomingmovie.model.MovieModel
import br.com.aron.upcomingmovie.model.UpcomingMovie
import br.com.aron.upcomingmovie.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.com.aron.upcomingmovie.R

/**
 * Created by Aron on 23/02/2018.
 */
class GetUpcomingWebClient {

    fun getUpcoming(context: Context, apiKey: String, language: String, page: Int, region: String,
                    callbackResponse: GetUpcomingResponse<List<MovieModel>>) {

        val call = RetrofitClientTMDB().getUpcomingService().getUpcoming(apiKey, language, page, region)
        call.enqueue(object: Callback<UpcomingMovie> {
            override fun onResponse(call: Call<UpcomingMovie?>?,
                                    response: Response<UpcomingMovie?>?) {

                Log.d("sucesso", "sucesso")
                response?.body().let {

                    val movie: UpcomingMovie = it!!
                    val results = movie.results
                    callbackResponse.success(results!!)
                }
            }

            override fun onFailure(call: Call<UpcomingMovie?>?,
                                   t: Throwable?) {

                Log.e("onFailure error", t?.message)
                Utils().showDialogWithoutCancel(context,context.getString(R.string.error),
                        context.getString(R.string.message_error),null)
            }
        })
    }

}