package uk.co.diegobarle.core.datamanager

/**
 * Class used to wrap data requests with their appropriate status
 * @param data Data gotten from the source / result. Mandatory for SUCCESS status. Optional for ERROR and LOADING
 * @param error Error thrown when requesting the data. Mandatory for ERROR status. Not used anywhere else
 * @param status Status of the result.
 */
data class DataResult<out T>(val status: Status, val data: T?, val error: Throwable?) {

    companion object {
        /**
         * Creates a DataResult object of the status type Success.
         * @param data Data attached to the result.
         */
        fun <T> success(data: T): DataResult<T> {
            return DataResult(Status.SUCCESS, data, null)
        }

        /**
         * Creates a DataResult object of the status type Error.
         * @param data Optional value for data that was able to be requested
         */
        fun <T> error(error: Throwable, data: T? = null): DataResult<T> {
            return DataResult(Status.ERROR, data, error)
        }

        /**
         * Creates a DataResult object of the status type Loading.
         * @param data Optional value for data that was able to be requested
         */
        fun <T> loading(data: T? = null): DataResult<T> {
            return DataResult(Status.LOADING, data, null)
        }
    }
}