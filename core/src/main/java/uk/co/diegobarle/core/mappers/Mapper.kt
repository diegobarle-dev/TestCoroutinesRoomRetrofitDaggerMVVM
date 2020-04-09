package uk.co.diegobarle.core.mappers

import uk.co.diegobarle.core.model.entity.ClientModel
import uk.co.diegobarle.core.network.servermodel.ServerModel

/**
 * Basic mapper
 */
interface Mapper<From, To>{
    fun map(from: From): To
}

/**
 * Interface to map a ServerModel into a ClientModel
 */
interface MapperFromServer<Local: ClientModel, Server: ServerModel> {
    fun fromServer(from: Server): Local
}

/**
 * Interface to map a ClientModel into a ServerModel
 */
interface MapperToServer<Local: ClientModel, Server: ServerModel> {
    fun toServer(from: Local): Server
}