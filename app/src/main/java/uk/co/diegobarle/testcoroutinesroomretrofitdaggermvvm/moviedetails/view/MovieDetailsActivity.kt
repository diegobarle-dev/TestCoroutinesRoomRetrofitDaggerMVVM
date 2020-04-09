package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.moviedetails.view

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_movie_details.*
import uk.co.diegobarle.core.datamanager.DataResult
import uk.co.diegobarle.core.datamanager.Status
import uk.co.diegobarle.core.datautil.ApiImageUtils
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.BaseActivity
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.R
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.di.injector
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.moviedetails.model.VMovieDetails
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.moviedetails.viewmodel.MovieDetailsViewModel
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.util.MOVIE_ID_BUNDLE_KEY

class MovieDetailsActivity : BaseActivity() {

    private var movieId: Int = -1

    private val viewModel: MovieDetailsViewModel by lazy {
        ViewModelProvider(this, injector.movieDetailsViewModelFactory()).get(MovieDetailsViewModel::class.java)
    }

    private var getMovieLD: LiveData<DataResult<VMovieDetails>>? = null

    private var getMovieObserver = Observer<DataResult<VMovieDetails>> {
        when(it.status){
            Status.SUCCESS -> bindMovie(it.data!!)
            Status.ERROR -> { /* TODO display error message */}
            Status.LOADING -> { /* TODO display a loader */}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movieId = intent.getIntExtra(MOVIE_ID_BUNDLE_KEY, -1)
        if(movieId < 0) {
            finish()
            return
        }

        initViews()
        initViewModels()
    }

    private fun initViews(){
        buttonClose.setOnClickListener {
            finish()
        }
    }

    private fun initViewModels(){
        getMovieLD?.removeObserver(getMovieObserver)
        getMovieLD =  viewModel.getMovie(movieId)
        getMovieLD?.observe(this, getMovieObserver)
    }

    private fun bindMovie(movie: VMovieDetails){
        tvTitle.text = movie.title
        glide.load(ApiImageUtils.getUrlFromPosterPath(movie.posterPath?:"", resources))
            .centerCrop()
            .into(ivMovie)
        tvReleaseDate.text = movie.releaseDate
        ratingBar.rating = movie.voteAverage
        tvTotalVotes.text = movie.voteCount.toString()
        tvDescription.text = movie.overview
    }
}
