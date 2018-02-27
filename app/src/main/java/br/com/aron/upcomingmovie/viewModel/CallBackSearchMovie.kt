package br.com.aron.upcomingmovie.viewModel

/**
 * Created by Aron on 27/02/2018.
 */
interface CallBackSearchMovie<T> {
    fun success(response: T)
}