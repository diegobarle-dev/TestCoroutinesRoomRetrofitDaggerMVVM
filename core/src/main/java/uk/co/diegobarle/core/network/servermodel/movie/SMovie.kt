package uk.co.diegobarle.core.network.servermodel.movie

import com.google.gson.annotations.SerializedName
import uk.co.diegobarle.core.network.servermodel.ServerModel

data class SMovie(
    @SerializedName("poster_path") val posterPath: String?, //"poster_path": "/IfB9hy4JH1eH6HEfIgIGORXi5h.jpg"
    @SerializedName("adult") val adult: Boolean?, //"adult": false
    @SerializedName("overview") val overview: String?, //"overview": "Jack Reacher must uncover the truth behind a major government conspiracy in order to clear his name. On the run as a fugitive from the law, Reacher uncovers a potential secret from his past that could change his life forever."
    @SerializedName("release_date") val releaseDate: String?, //"release_date": "2016-10-19"
    @SerializedName("genre_ids") val genresIds: List<Int>?, //"genre_ids": [53,28,80,18,9648]
    @SerializedName("id") val id: Int, //"id": 343611
    @SerializedName("original_title") val originalTitle: String?, //"original_title": "Jack Reacher: Never Go Back"
    @SerializedName("original_language") val originalLanguage: String?, //"original_language": "en"
    @SerializedName("title") val title: String?, //"title": "Jack Reacher: Never Go Back"
    @SerializedName("backdrop_path") val backdropPath: String?, //"backdrop_path": "/4ynQYtSEuU5hyipcGkfD6ncwtwz.jpg"
    @SerializedName("popularity") val popularity: Double?, //"popularity": 26.818468
    @SerializedName("vote_count") val voteCount: Int?, //"vote_count": 201
    @SerializedName("video") val video: Boolean?, //"video": false
    @SerializedName("vote_average") val voteAverage: Double? //"vote_average": 4.19
): ServerModel(){
    override fun init() { }

}