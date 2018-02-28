package br.com.aron.upcomingmovie.view.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
import br.com.aron.upcomingmovie.R
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.model.MovieModel
import br.com.aron.upcomingmovie.utils.Utils
import br.com.aron.upcomingmovie.view.adapter.SearchMovieAdapter
import br.com.aron.upcomingmovie.viewModel.SearchMovieViewModel
import kotlinx.android.synthetic.main.search_movie_activity.*

class SearchMovieActivity : AppCompatActivity(), View.OnClickListener, SearchMovieViewModel.SearchMovieViewModelListener {

    private var searchMovieListView: ListView? = null
    private var movieList: MutableList<MovieModel> = mutableListOf()
    private var genreList: MutableList<Genre>? = mutableListOf()
    private var mAdapter: SearchMovieAdapter? = null

    private var searchMoviewViewModel = SearchMovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_movie_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""

        act_search_btnSearch.setOnClickListener(this)

        searchMovieListView = findViewById(R.id.search_movie_listView)
        searchMoviewViewModel.requestGenreList(this,this)
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

                    label_no_movie_found.visibility = View.GONE

                    if (searchView.text.toString() == ""){

                        Utils().showDialogWithoutCancel(this,this.getString(R.string.attention),
                                this.getString(R.string.empty_text),null)
                    }else {

                        search_progress_movie.visibility = View.VISIBLE
                        movieList.clear()
                        searchMoviewViewModel.requestSearchMovie(this, searchView.text.toString(), this)

                        val inputManager:InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)
                    }
                }
            }
        }
    }

    override fun returnGenreListListener(returnList: MutableList<Genre>) {
        genreList = returnList
    }

    override fun returnMovieListListener(returnList: MutableList<MovieModel>) {

        if (returnList.count() == 0){
            label_no_movie_found.visibility = View.VISIBLE
        }else {
            label_no_movie_found.visibility = View.GONE
        }

        mAdapter = SearchMovieAdapter(this, returnList, genreList!!)
        (searchMovieListView as ListView).adapter = mAdapter
        search_progress_movie.visibility = View.GONE
        mAdapter?.notifyDataSetChanged()
    }
}