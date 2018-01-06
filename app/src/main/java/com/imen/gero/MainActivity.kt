package com.imen.gero

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), MovieRecycleViewAdapter.MovieAdapterInteraction {

    lateinit var movieAdapter: MovieRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initRecycleView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_add_item -> {
                Log.i("onOptionsItemSelected"," addItem")
                movieAdapter.addItem(Movie("The random King",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore",
                        R.drawable.king_ragnar))
                return true
            }

            R.id.action_delete_item -> {
                Log.i("onOptionsItemSelected", " deleteItem")
                movieAdapter.removeFirstItem()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Method to initialize the recycleView
     */
    private fun initRecycleView(){
        list_movie.layoutManager = LinearLayoutManager(this)
        movieAdapter= MovieRecycleViewAdapter(generateMovieList() , this)
        list_movie.adapter = movieAdapter
    }

    /**
     * Method generate a list of Movie
     */
    private fun generateMovieList() : ArrayList<Movie> {
        var movies = ArrayList<Movie>()
        movies.add(Movie("The king 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.gog))
        movies.add(Movie("The king 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.got))
        movies.add(Movie("The king 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.starwars))
        movies.add(Movie("The king 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.spiderman))
        movies.add(Movie("The king 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.gog))
        movies.add(Movie("The king 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.starwars))
        movies.add(Movie("The king 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.got))
        movies.add(Movie("The king 8", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.king_ragnar))
        movies.add(Movie("The king 9", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.spiderman))
        movies.add(Movie("The king 10", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.starwars))
        movies.add(Movie("The king 11", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore", R.drawable.king_ragnar))
        return movies
    }

    /******************* MovieAdapterInteraction Callback Methods********/

    /**
     * Method to show the description in a toast
     * @param desc : description of the movie selected
     */
    override fun showDescriptionToast(desc: String) {
        Toast.makeText(this, desc, Toast.LENGTH_LONG).show()
    }

    /**
     * Method to share a movie
     * @param movie: the object Movie
     */
    override fun shareMovie(movie: Movie) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, "Hello,\nWatch the movie " + movie.title)
        intent.type = "text/plain"
        startActivity(intent)
    }

    /***************************/

}
