package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.view

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_movies_list.*
import uk.co.diegobarle.core.datamanager.DataResult
import uk.co.diegobarle.core.datamanager.Status
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.BaseActivity
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.R
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.di.injector
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.moviedetails.view.MovieDetailsActivity
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.model.VMovie
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.viewmodel.MoviesListViewModel
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.util.MOVIE_ID_BUNDLE_KEY

class MoviesListActivity : BaseActivity(), MoviesBaseAdapter.Listener {

    private val viewModel: MoviesListViewModel by lazy {
        ViewModelProvider(this, injector.moviesListViewModelFactory()).get(MoviesListViewModel::class.java)
    }

    private lateinit var adapter: MoviesListAdapter
    private var searchMoviesLD: LiveData<DataResult<List<VMovie>>>? = null
    private var searchMoviesObserver = Observer<DataResult<List<VMovie>>> {
        when(it.status){
            Status.SUCCESS -> adapter.initMovies(it.data!!)
            Status.ERROR -> { /* TODO display error message */}
            Status.LOADING -> { /* TODO display a loader */}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        setupViews()
        initViewModels()
    }

    private fun setupViews(){
        adapter = MoviesListAdapter(this, glide)
        listMovies.adapter = adapter
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{ searchMovies(query) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean { return false }

        })
    }

    private fun initViewModels(){
        searchMovies("Life")

        viewModel.fetchPopularMovies().observe(this, Observer {
            if(it.status == Status.SUCCESS){
                adapter.initPopularMovies(it.data!!)
            }
        })
    }

    fun searchMovies(query: String){
        searchMoviesLD?.removeObserver(searchMoviesObserver)
        searchMoviesLD = viewModel.fetchMovies(query)
        searchMoviesLD?.observe(this, searchMoviesObserver)
    }

    override fun onMovieSelected(item: VMovie) {
        startActivity(Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra(MOVIE_ID_BUNDLE_KEY, item.id)
        })
    }
}
