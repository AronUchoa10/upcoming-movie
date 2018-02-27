package br.com.aron.upcomingmovie.data.callbacks

/**
 * Created by Aron on 25/02/2018.
 */
interface GetUpcomingResponse<T> {
    fun success(response: T)
}