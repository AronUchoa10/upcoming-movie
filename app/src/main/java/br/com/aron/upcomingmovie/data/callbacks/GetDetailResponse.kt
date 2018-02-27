package br.com.aron.upcomingmovie.data.callbacks

/**
 * Created by Aron on 26/02/2018.
 */
interface GetDetailResponse<T> {
    fun success(response: T)
}