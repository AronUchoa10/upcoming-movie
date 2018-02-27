package br.com.aron.upcomingmovie.view.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.aron.upcomingmovie.model.MovieModel
import br.com.aron.upcomingmovie.R
import br.com.aron.upcomingmovie.model.Genre
import br.com.aron.upcomingmovie.utils.Constants
import br.com.aron.upcomingmovie.utils.Utils
import br.com.aron.upcomingmovie.view.activity.MovieDetailActivity

/**
 * Created by Aron on 26/02/2018.
 */
open class SearchMovieAdapter(private val activity: Activity, movieList: List<MovieModel>, genreList: List<Genre>) : BaseAdapter() {

    private var movieList = ArrayList<MovieModel>()
    private var genreList = ArrayList<Genre>()

    init {
        this.movieList = movieList as ArrayList<MovieModel>
        this.genreList = genreList as ArrayList<Genre>
    }

    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    @SuppressLint("InflateParams", "ViewHolder")
    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View? {

        val view: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.item_movie, null)

        val imageMovie = view.findViewById<ImageView>(R.id.image_movie)
        val labelName = view.findViewById<TextView>(R.id.label_name)
        val labelGenre = view.findViewById<TextView>(R.id.label_genre)
        val labelReleaseDate = view.findViewById<TextView>(R.id.label_release_date)
        val labelNoImage = view.findViewById<TextView>(R.id.without_image_movie)

        labelName.text = movieList[i].title
        val releaseDate = activity.getString(R.string.release_date) + movieList[i].releaseDate
        labelReleaseDate.text = releaseDate

        if (movieList[i].backdropPath == null){
            if (movieList[i].posterPath == null) {
                labelNoImage.visibility = View.VISIBLE
            }else {
                Utils().glideLoadCircleImage(activity, Constants.imageUrl +
                        movieList[i].posterPath, imageMovie)
            }
        }else {
            Utils().glideLoadCircleImage(activity, Constants.imageUrl +
                    movieList[i].backdropPath, imageMovie)
        }

        var genres = ""
        for (id in movieList[i].genreIds!!) {
            for (item in genreList){
                if (id == item.id){
                    genres += item.name!! + ", "
                }
            }
        }
        if (genres.length > 2){
            val genreToLabel = genres.substring(0, genres.length - 2)
            val genreText = activity.getString(R.string.genre) + genreToLabel
            labelGenre.text = genreText
        }else{
            val genreText = activity.getString(R.string.genre) + activity.getString(R.string.unspecified_genre)
            labelGenre.text = genreText
        }

        view.setOnClickListener {

            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(Constants.movieIDIntent, movieList[i].id)
            activity.startActivity(intent)
        }

        return view
    }
}


//class SearchMovieAdapter(private val activity: Activity, private val moviesList: List<MovieModel>, genreList: ArrayList<Parcelable>) : RecyclerView.Adapter<SearchMovieAdapter.MyViewHolder>() {
//
////    private var itemClick: ((MovieModel) -> Unit)? = null
////    private var genreList : List<Genre>() = genreList
//
//    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var imageMovie = view.findViewById<ImageView>(R.id.image_movie)!!
//        var labelName = view.findViewById<TextView>(R.id.label_name)!!
//        var labelGenre = view.findViewById<TextView>(R.id.label_genre)!!
//        val labelReleaseDate = view.findViewById<TextView>(R.id.label_release_date)!!
//        val labelNoImage = view.findViewById<TextView>(R.id.without_image_movie)!!
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_movie, parent, false)
//
//        return MyViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val Movies = moviesList[position]
//        val Genres = genreList
//        holder.labelName.text = Movies.title
//        holder.labelGenre.text = Movies.title
//        holder.labelReleaseDate.text = Movies.releaseDate
//
//        if (Movies.backdropPath == null){
//            if (Movies.posterPath == null) {
//                holder.labelNoImage.visibility = View.VISIBLE
//            }else {
//                Utils().glideLoadCircleImage(activity, Constants.imageUrl +
//                        Movies.posterPath, holder.imageMovie)
//            }
//        }else {
//            Utils().glideLoadCircleImage(activity, Constants.imageUrl +
//                    Movies.backdropPath, holder.imageMovie)
//        }
//
//        var genres = ""
//        for (id in Movies.genreIds!!) {
//            for (item in genreList){
//                if (id == item.id){
//                    genres += item.name!! + ", "
//                }
//            }
//        }
//        if (genres.length > 2){
//            val genreToLabel = genres.substring(0, genres.length - 2)
//            val genreText = activity.getString(R.string.genre) + genreToLabel
//            holder.labelGenre.text = genreText
//        }else{
//            val genreText = activity.getString(R.string.genre) + activity.getString(R.string.unspecified_genre)
//            holder.labelGenre.text = genreText
//        }
//
////        // Calling the clickListener sent by the constructor
////        holder.setOnClickListener { clickListener(item) }
//
////        view.setOnClickListener {
////
////            val intent = Intent(activity, MovieDetailActivity::class.java)
////            intent.putExtra(Constants.movieIDIntent, movieList[i].id)
////            activity.startActivity(intent)
////        }
//    }
//
//    override fun getItemCount(): Int {
//        return moviesList.size
//    }
//}