package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.R
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.model.VMovie

class MoviesListAdapter (listener: Listener, glide: RequestManager):MoviesBaseAdapter(listener, glide) {

    private val popularMovies = ArrayList<VMovie>()

    fun initPopularMovies(items: List<VMovie>?){
        this.popularMovies.clear()
        items?.let { this.popularMovies.addAll(it) }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0 && popularMovies.isNotEmpty()) HORIZONTAL_LIST_VIEW_TYPE
        else super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesBaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            HORIZONTAL_LIST_VIEW_TYPE -> HorizontalListViewHolder(layoutInflater.inflate(R.layout.view_movies_list_item_horizontal, null, false))
            else -> super.onCreateViewHolder(parent, viewType)
        }
    }

    override fun getItemCount() = searchedMoviesStartingIndex() + super.getItemCount()

    private fun searchedMoviesStartingIndex() = if(popularMovies.isEmpty()) 0 else 1

    override fun onBindViewHolder(holder: MoviesBaseViewHolder, position: Int) {
        when(holder){
            is HorizontalListViewHolder -> holder.bind(popularMovies)
            else -> super.onBindViewHolder(holder, position - searchedMoviesStartingIndex())
        }
    }

    inner class HorizontalListViewHolder(view: View): MoviesBaseViewHolder(view){
        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val listMostPopular = view.findViewById<RecyclerView>(R.id.listMostPopular)
        private val adapter = object: MoviesBaseAdapter(listener, glide){
            override fun getMovieView(parent: ViewGroup, viewType: Int): View {
                return LayoutInflater.from(parent.context).inflate(R.layout.view_horizontal_list_item, parent, false)
            }
        }
        init {
            tvTitle.text = view.resources.getString(R.string.most_popular)
            listMostPopular.adapter = adapter
        }

        fun bind(list: List<VMovie>){
            adapter.initMovies(list)
        }
    }

    companion object{
        const val HORIZONTAL_LIST_VIEW_TYPE = 1
    }
}