package br.com.aron.upcomingmovie.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import br.com.aron.upcomingmovie.R
import br.com.aron.upcomingmovie.utils.Constants
import kotlinx.android.synthetic.main.movie_detail_activity.*
import br.com.aron.upcomingmovie.data.callbacks.GetDetailResponse
import br.com.aron.upcomingmovie.data.webclients.GetDetailWebClient
import br.com.aron.upcomingmovie.model.MovieDetail
import br.com.aron.upcomingmovie.utils.Utils


class MovieDetailActivity : AppCompatActivity() {

    private var movieID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)

        setSupportActionBar(toolbar)
        displayHomeAsUpEnabled()
        toolbar.setOnClickListener {
            finish()
        }

        movieID = intent.getIntExtra(Constants.movieIDIntent, -1)
        if (movieID != -1){
            requestGetDetail(movieID)
        }
        Log.d("Movie ID", movieID.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayHomeAsUpEnabled() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun populateMovieDetail(movieDetail: MovieDetail){

        progress_movie_detail.visibility = View.GONE


        val movieName = getString(R.string.name) + movieDetail.title
        val releaseDate = getString(R.string.release_date) + movieDetail.releaseDate
        val overview = movieDetail.overview

        Utils().glideLoadCircleImage(this, Constants.imageUrl + movieDetail.posterPath, image_movie)
        label_name.text = movieName
        label_genre.text = movieDetail.title
        label_release_date.text = releaseDate
        label_title_overview.visibility = View.VISIBLE
        label_overview.text = overview

        var genres = ""
        for (item in movieDetail.genres!!) {
            genres += item.name!! + ", "
        }
        if (genres.length > 2){
            val genreToLabel = genres.substring(0, genres.length - 2)
            val genreText = this.getString(R.string.genre) + genreToLabel
            label_genre.text = genreText
        }else{
            val genreText = this.getString(R.string.genre) + this.getString(R.string.unspecified_genre)
            label_genre.text = genreText
        }
    }

    // Service
    // GetDetail
    private fun requestGetDetail(movieID: Int) {

        GetDetailWebClient().getDetails(this, movieID, Constants.apiKey, Constants.language,
                "", object : GetDetailResponse<MovieDetail> {
                    override fun success(response: MovieDetail) {
//                        movieDetail = response
                        populateMovieDetail(response)
                    }
                })
    }
}
