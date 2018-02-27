package br.com.aron.upcomingmovie.data.webclients

import android.content.Context
import android.util.Log
import br.com.aron.upcomingmovie.R
import br.com.aron.upcomingmovie.data.RetrofitClientTMDB
import br.com.aron.upcomingmovie.data.callbacks.GetDetailResponse
import br.com.aron.upcomingmovie.data.callbacks.GetGenreListResponse
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.model.MovieDetail
import br.com.aron.upcomingmovie.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Aron on 26/02/2018.
 */
class GetDetailWebClient {

    fun getDetails(context: Context, movieID: Int, apiKey: String, language: String, appendToResponse: String,
                   callbackResponse: GetDetailResponse<MovieDetail>){

        val call = RetrofitClientTMDB().getDetailService().getDetails(movieID, apiKey, language, appendToResponse)
        call.enqueue(object: Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail?>?,
                                    response: Response<MovieDetail?>?) {

                Log.d("sucesso", "sucesso")
                response?.body().let {

                    val movieDetail: MovieDetail = it!!
                    callbackResponse.success(movieDetail!!)
                }
            }

            override fun onFailure(call: Call<MovieDetail?>?,
                                   t: Throwable?) {

                Log.e("onFailure error", t?.message)
                Utils().showDialogWithoutCancel(context,context.getString(R.string.error),
                        context.getString(R.string.message_error),null)
            }
        })
    }
}