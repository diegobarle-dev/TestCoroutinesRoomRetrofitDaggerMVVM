package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.moviedetails.model

data class VMovieDetails(
    var id: Int,
    var posterPath: String?,
    var overview: String?,
    var releaseDate: String?,
    var title: String?,
    var popularity: Double,
    var voteCount: Int,
    var voteAverage: Float
)