package uk.co.diegobarle.core.datautil

import android.content.res.Resources
import uk.co.diegobarle.core.R

object ApiImageUtils {
    fun getUrlFromPosterPath(posterPath: String, resources: Resources) =
        resources.getString(R.string.base_api_image_url_w200).plus(posterPath)
}