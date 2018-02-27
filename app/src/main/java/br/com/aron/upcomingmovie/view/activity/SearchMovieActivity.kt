package br.com.aron.upcomingmovie.view.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
import br.com.aron.upcomingmovie.R
import br.com.aron.upcomingmovie.data.callbacks.GetGenreListResponse
import br.com.aron.upcomingmovie.data.callbacks.SearchMovieResponse
import br.com.aron.upcomingmovie.data.webclients.GetGenreListWebClient
import br.com.aron.upcomingmovie.data.webclients.SearchMovieWebClient
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.model.MovieModel
import br.com.aron.upcomingmovie.utils.Constants
import br.com.aron.upcomingmovie.utils.Utils
import br.com.aron.upcomingmovie.view.adapter.SearchMovieAdapter
import br.com.aron.upcomingmovie.viewModel.SearchMovieViewModel
import kotlinx.android.synthetic.main.search_movie_activity.*

class SearchMovieActivity : AppCompatActivity(), View.OnClickListener {
    private var searchMovieListView: ListView? = null
    private val movieList: MutableList<MovieModel> = mutableListOf()
    private var genreList: MutableList<Genre>? = mutableListOf()
    private var mAdapter: SearchMovieAdapter? = null

    private var searchMoviewViewModel: SearchMovieViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_movie_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.title = ""

        act_search_btnSearch.setOnClickListener(this)

        searchMovieListView = findViewById<ListView>(R.id.search_movie_listView)

        mAdapter = SearchMovieAdapter(this, movieList, genreList!!)
        (searchMovieListView as ListView).adapter = mAdapter

        requestGenreList()
//        genreList = searchMoviewViewModel!!.requestGenreList(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {

        if (v != null) {
            when(v.id) {
                R.id.act_search_btnSearch -> {

                    if (searchView.text.toString() == ""){

                        Utils().showDialogWithoutCancel(this,this.getString(R.string.attention),
                                this.getString(R.string.empty_text),null)
                    }else {

                        search_progress_movie.visibility = View.VISIBLE
                        movieList.clear()
                        requestSearchMovie(searchView.text.toString())
                        val inputManager:InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)
                    }
                }
            }
        }
    }

    // GetGenreList
    private fun requestSearchMovie(query: String) {

        SearchMovieWebClient().searchMovie(this, Constants.apiKey, Constants.language, query,
                object: SearchMovieResponse<List<MovieModel>> {
                    override fun success(response: List<MovieModel>) {
                        for (item in response) {
                            this@SearchMovieActivity.movieList.add(item)
                        }
                        search_progress_movie.visibility = View.GONE
                        mAdapter?.notifyDataSetChanged()
                    }
                })
    }

    // GetGenreList
    fun requestGenreList()  {

        GetGenreListWebClient().getGenreList(this, Constants.apiKey, Constants.language,
                object : GetGenreListResponse<List<Genre>> {
                    override fun success(response: List<Genre>) {
                        for (item in response) {
                            this@SearchMovieActivity.genreList!!.add(item)
                        }
                    }
                })
    }

}
