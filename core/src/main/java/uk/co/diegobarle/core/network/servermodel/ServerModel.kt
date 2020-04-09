package uk.co.diegobarle.core.network.servermodel

/**
 * Class representing a model from the server
 */
abstract class ServerModel {

    /**
     * Server objects might be modified or have a different implementations to what the client needs.
     * This method allows adding some logic required to help mapping server models
     */
    abstract fun init()
}