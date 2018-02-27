package br.com.aron.upcomingmovie.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import br.com.aron.upcomingmovie.R
import br.com.aron.upcomingmovie.data.callbacks.GetGenreListResponse
import br.com.aron.upcomingmovie.data.callbacks.GetUpcomingResponse
import br.com.aron.upcomingmovie.data.webclients.GetGenreListWebClient
import br.com.aron.upcomingmovie.data.webclients.GetUpcomingWebClient
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.model.MovieModel
import br.com.aron.upcomingmovie.utils.Constants
import br.com.aron.upcomingmovie.view.adapter.MovieAdapter
import br.com.aron.upcomingmovie.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.movie_activity.*



class MovieActivity : AppCompatActivity() {

    private var movieListView: ListView? = null
    private val movieList: MutableList<MovieModel> = mutableListOf()
    private val genreList: MutableList<Genre>? = mutableListOf()
    private var adapter: MovieAdapter? = null

    private val firstPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity)

//        supportActionBar!!.title = "Teste"

//        val s = SpannableString(title)
//        s.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.black_color)),
//                0, title.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        supportActionBar!!.title = s

        movieListView = findViewById<ListView>(R.id.upcoming_movie_list)

        adapter = MovieAdapter(this, movieList, genreList!!)
        (movieListView as ListView).adapter = adapter

        // endless Scroll list
        movieListView!!.setOnScrollListener(object : EndlessScrollListener() {
            override fun onLoadMore(page: Int, totalItemsCount: Int): Boolean {
                requestUpcomingMovie(page)

                return true // ONLY if more data is actually being loaded; false otherwise.
            }
        })

        requestGenreList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            android.R.id.home -> finish()

            R.id.menuSearch -> {

                val intent = Intent(this@MovieActivity, SearchMovieActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    // services

    // GetUpcomingMovie
    private fun requestUpcomingMovie(numberPage: Int) {

        if (numberPage == firstPage){
            progress_movie.visibility = View.VISIBLE
            upcoming_movie_list.visibility = View.GONE
        }

        GetUpcomingWebClient().getUpcoming(this,Constants.apiKey,Constants.language,
                numberPage, "",object : GetUpcomingResponse<List<MovieModel>> {
            override fun success(response: List<MovieModel>) {
                if (numberPage == firstPage){
                    progress_movie.visibility = View.GONE
                    upcoming_movie_list.visibility = View.VISIBLE
                }
                for (item in response) {
                    this@MovieActivity.movieList.add(item)
                }
                adapter?.notifyDataSetChanged()
            }
        })
    }

    // GetGenreList
    private fun requestGenreList() {

        GetGenreListWebClient().getGenreList(this,Constants.apiKey, Constants.language,
                object : GetGenreListResponse<List<Genre>> {
                    override fun success(response: List<Genre>) {
                        if (genreList!!.count() != 0) {
                            genreList.clear()
                            movieList.clear()
                        }
                        requestUpcomingMovie(firstPage)
                        for (item in response) {
                            this@MovieActivity.genreList.add(item)
                        }
                        adapter?.notifyDataSetChanged()
                    }
                })
    }
}