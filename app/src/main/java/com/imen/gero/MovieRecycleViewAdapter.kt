package com.imen.gero

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class MovieRecycleViewAdapter(private var movies: ArrayList<Movie>, private val movieAdapterInteraction: MovieAdapterInteraction) : RecyclerView.Adapter<MovieRecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitleTxt.text = movies[position].title
        holder.movieDescriptionTxt.text = movies[position].description
        holder.movieImage.setImageDrawable(movieAdapterInteraction.getDrawable(movies[position].drawableId))
        holder.movieShareBtn.setOnClickListener {
            movieAdapterInteraction.shareMovie(movie = movies[position])
        }

        holder.movieDetailsBtn.setOnClickListener{
                movieAdapterInteraction.showDescriptionToast(movies[position].description!!)
        }
    }

    /**
     * Method to  get count of the items
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Method to add an item of type Movie
     * @param movie
     */
    fun addItem(movie:Movie){
        if(movies.size >1) {
            movies.add(1, movie)
            notifyItemInserted(1)
        }
    }

    /**
     * Method to remove the first Item in list
     */
    fun removeFirstItem(){
        if(!movies.isEmpty()) {
            movies.removeAt(0)
            notifyItemRemoved(0)
        }
    }

    /**
     * Class represent the view holder of the item of recycleView
     */
    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val movieImage: ImageView = mView.findViewById<View>(R.id.movie_image) as ImageView
        val movieTitleTxt = mView.findViewById<View>(R.id.movie_title) as TextView
        val movieDescriptionTxt = mView.findViewById<View>(R.id.movie_description) as TextView
        val movieShareBtn = mView.findViewById<View>(R.id.movie_share) as Button
        val movieDetailsBtn = mView.findViewById<View>(R.id.movie_details) as Button
    }

    /**
     * A Callback to manage the interaction
     * between Activity/Fragment and the adpater
     */
    interface MovieAdapterInteraction {
        fun getDrawable(@DrawableRes drawableRes: Int): Drawable
        fun showDescriptionToast(desc : String)
        fun shareMovie(movie: Movie)
    }
}
