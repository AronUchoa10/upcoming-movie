package br.com.aron.upcomingmovie.data.callbacks

/**
 * Created by Aron on 26/02/2018.
 */
interface SearchMovieResponse<T> {
    fun success(response: T)
}