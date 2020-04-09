package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import uk.co.diegobarle.core.datautil.ApiImageUtils
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.R
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.model.VMovie

open class MoviesBaseAdapter (var listener: Listener, val glide: RequestManager): RecyclerView.Adapter<MoviesBaseAdapter.MoviesBaseViewHolder>() {

    private val movies = ArrayList<VMovie>()

    fun initMovies(items: List<VMovie>?){
        this.movies.clear()
        items?.let { this.movies.addAll(it) }
        notifyDataSetChanged()
    }

    @CallSuper
    override fun getItemViewType(position: Int): Int {
        return MOVIE_VIEW_TYPE
    }

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesBaseViewHolder {
        return when(viewType){
            MOVIE_VIEW_TYPE -> MovieViewHolder(getMovieView(parent, viewType))
            else -> throw IllegalStateException("Item type not supported")
        }
    }

    @CallSuper
    override fun getItemCount() = movies.size

    @CallSuper
    override fun onBindViewHolder(holder: MoviesBaseViewHolder, position: Int) {
        when(holder){
            is MovieViewHolder -> holder.bind(movies[position])
        }
    }

    open fun getMovieView(parent: ViewGroup, viewType: Int): View{
        return LayoutInflater.from(parent.context).inflate(R.layout.view_movies_list_item, parent, false)
    }

    abstract inner class MoviesBaseViewHolder(view: View): RecyclerView.ViewHolder(view)

    inner class MovieViewHolder(view: View): MoviesBaseViewHolder(view){
        private val ivMovie = view.findViewById<ImageView?>(R.id.ivMovie)
        private val tvTitle = view.findViewById<TextView?>(R.id.tvTitle)
        private val tvReleaseDate = view.findViewById<TextView?>(R.id.tvReleaseDate)
        private val ratingBar = view.findViewById<RatingBar?>(R.id.ratingBar)
        private val tvTotalVotes = view.findViewById<TextView?>(R.id.tvTotalVotes)


        fun bind(item: VMovie){
            ivMovie?.let{
                glide.load(ApiImageUtils.getUrlFromPosterPath(item.posterPath?:"", itemView.resources))
                .centerCrop()
                .into(it)
            }
            tvTitle?.text = item.title
            tvReleaseDate?.text = item.releaseDate
            ratingBar?.rating = item.voteAverage
            tvTotalVotes?.text = item.voteCount.toString()

            itemView.setOnClickListener {
                listener.onMovieSelected(item)
            }
        }
    }

    interface Listener{
        fun onMovieSelected(item: VMovie)
    }

    companion object{
        const val MOVIE_VIEW_TYPE = -1
    }
}