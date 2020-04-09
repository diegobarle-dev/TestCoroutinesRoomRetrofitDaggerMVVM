package uk.co.diegobarle.core.datamanager

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import uk.co.diegobarle.core.network.servermodel.SResponse
import uk.co.diegobarle.core.network.servermodel.ServerModel
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that handles CRUD requests for local and remote data.
 */
@Singleton
class DataManager @Inject constructor(){

    /**
     * Method to do a Database / Server / Database + Server requests for data
     *
     * The database is the only source of data for any consumer. Any updates are only done from
     * database. The consumer can get data with the next results:
     * [Status.SUCCESS] - with data from database
     * [Status.ERROR] - if error has occurred from any source
     * [Status.LOADING] - while the data is being fetched
     *
     * @param requestLevel Type of call to execute.
     * @param dbCall Function to execute when getting data from database
     * @param networkCall Function to execute when requesting data from server
     * @param dbSaveCall Function to execute when storing data brought from server
     * @param mapperCall Function to execute to map a Server object into a Client object
     *
     * @return LiveData that allows to observe the data from database.
     */
    fun <Server, Local> resultLiveData(
        requestLevel: RequestLevel,
        dbCall: suspend () -> LiveData<Local>?,
        networkCall: suspend () -> Response<SResponse<Server>>,
        dbSaveCall: suspend (local: Local) -> Unit,
        mapperCall: (server: Server) -> Local
    ): LiveData<DataResult<Local>> =
        liveData(Dispatchers.IO) {
            try {
                var dabaseSource: LiveData<Local>? = null
                emit(DataResult.loading())
                if (requestLevel == RequestLevel.FULL_STACK || requestLevel == RequestLevel.DB_ONLY) {
                    try {
                        Log.d(TAG, "Fetching DB")
                        dabaseSource = dbCall()
                        dabaseSource?.let { emitSource(it.map { DataResult.success(it) }) }
                    }catch (e: Exception){
                        emit(DataResult.error(e))
                        Log.e(TAG, e.toString())
                    }
                }

                if (requestLevel == RequestLevel.FULL_STACK || requestLevel == RequestLevel.NETWORK_ONLY) {
                    var local: Local? = null
                    try {
                        Log.d(TAG, "Fetching Network")
                        val response = networkCall()
                        if (response.isSuccessful) {
                            val server = response.body()!!.results
                            (server as? ServerModel)?.init() //In case the object is a ServerModel
                            (server as? List<ServerModel>)?.forEach { it.init() } //In cae the object is a list of ServerModel
                            try {
                                Log.d(TAG, "Mapping Network")
                                local = mapperCall(server)

                                Log.d(TAG, "Storing Network")
                                dbSaveCall(local)

                                if (requestLevel == RequestLevel.NETWORK_ONLY) {
                                    // There's only need to emit on NETWORK_ONLY because on
                                    // DB_ONLY/FULL_STACK we are working with the database LiveData
                                    emit(DataResult.success(local))
                                }
                            }catch (e: Exception){
                                emit(DataResult.error(e))
                                Log.e(TAG, e.toString())
                            }
                        }else{
                            emit(DataResult.error(Exception("Unable to get data"), local))
                        }
                    }catch (e: UnknownHostException){
                        emit(DataResult.error(e, local))
                        Log.e(TAG, e.toString())
                    }catch (e: Exception){
                        emit(DataResult.error(e, local))
                        Log.e(TAG, e.toString())
                    }
                }
            }catch (e: Throwable){
                emit(DataResult.error(e))
                Log.e(TAG, e.toString())
            }

        }

    companion object{
        private const val TAG = "DataManager"
    }
}