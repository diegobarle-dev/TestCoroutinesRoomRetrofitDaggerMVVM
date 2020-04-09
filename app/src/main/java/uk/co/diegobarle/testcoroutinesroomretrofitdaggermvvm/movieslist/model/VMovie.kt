package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.model

data class VMovie(
    var id: Int,
    var posterPath: String?,
    var releaseDate: String?,
    var title: String?,
    var voteAverage: Float,
    var voteCount: Int
)