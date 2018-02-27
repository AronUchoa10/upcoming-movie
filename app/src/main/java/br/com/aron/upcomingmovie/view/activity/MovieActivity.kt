package br.com.aron.upcomingmovie.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import br.com.aron.upcomingmovie.R
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.model.MovieModel
import br.com.aron.upcomingmovie.view.adapter.MovieAdapter
import br.com.aron.upcomingmovie.utils.EndlessScrollListener
import br.com.aron.upcomingmovie.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.movie_activity.*

class MovieActivity : AppCompatActivity(), MovieViewModel.MovieViewModelListener {

    private var movieListView: ListView? = null
    private var movieList: MutableList<MovieModel> = mutableListOf()
    private var genreList: MutableList<Genre>? = mutableListOf()
    private var adapter: MovieAdapter? = null

    private var firstPage = 1

    var movieViewModel = MovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity)

        movieListView = findViewById<ListView>(R.id.upcoming_movie_list)

        // endless Scroll list
        movieListView!!.setOnScrollListener(object : EndlessScrollListener() {
            override fun onLoadMore(page: Int, totalItemsCount: Int): Boolean {
                movieViewModel.requestUpcomingMovie(page,this@MovieActivity,
                        this@MovieActivity)

                return true // ONLY if more data is actually being loaded; false otherwise.
            }
        })

        movieViewModel.requestGenreList(this,this)
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

    override fun returnMovieListListener(returnList: MutableList<MovieModel>, numberPage: Int) {
        if (numberPage == firstPage){
            progress_movie.visibility = View.GONE
            upcoming_movie_list.visibility = View.VISIBLE
        }
        for (item in returnList) {
            movieList.add(item)
        }
        adapter = MovieAdapter(this, movieList, genreList!!)
        (movieListView as ListView).adapter = adapter
        adapter?.notifyDataSetChanged()

    }

    override fun returnGenreListListener(returnList: MutableList<Genre>) {
        genreList = returnList
        progress_movie.visibility = View.VISIBLE
        upcoming_movie_list.visibility = View.GONE
        movieViewModel.requestUpcomingMovie(firstPage, this, this)
    }
}