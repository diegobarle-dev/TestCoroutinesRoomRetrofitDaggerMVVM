package uk.co.diegobarle.core.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "posterPath")
    var posterPath: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,

    @ColumnInfo(name = "genresIds")
    var genresIds: IdList,

    @ColumnInfo(name = "originalTitle")
    var originalTitle: String?,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int,

    @ColumnInfo(name = "video")
    var video: Boolean,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double
): ClientModel()