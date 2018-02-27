package br.com.aron.upcomingmovie.data.webclients

import android.content.Context
import android.util.Log
import br.com.aron.upcomingmovie.R
import br.com.aron.upcomingmovie.data.RetrofitClientTMDB
import br.com.aron.upcomingmovie.data.callbacks.GetGenreListResponse
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.model.GenreList
import br.com.aron.upcomingmovie.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Aron on 25/02/2018.
 */
class GetGenreListWebClient {

    fun getGenreList(context: Context, apiKey: String, language: String,
                     callbackResponse: GetGenreListResponse<MutableList<Genre>>) {

        val call = RetrofitClientTMDB().getGenreListService().getGenreList(apiKey, language)
        call.enqueue(object: Callback<GenreList> {
            override fun onResponse(call: Call<GenreList?>?,
                                    response: Response<GenreList?>?) {

                Log.d("sucesso", "sucesso")
                response?.body().let {

                    val genreList: GenreList = it!!
                    val results = genreList.genres
                    callbackResponse.success((results as MutableList<Genre>?)!!)
                }
            }

            override fun onFailure(call: Call<GenreList?>?,
                                   t: Throwable?) {

                Log.e("onFailure error", t?.message)
                Utils().showDialogWithoutCancel(context,context.getString(R.string.error),
                        context.getString(R.string.message_error),null)
            }
        })
    }
}