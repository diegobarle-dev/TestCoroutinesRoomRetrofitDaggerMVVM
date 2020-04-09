package uk.co.diegobarle.core.datamanager

/**
 * Defines types of request:
 *  - DB_ONLY: executes only local database CRUD functions
 *  - NETWORK_ONLY: executes only remote server CRUD functions
 *  - FULL_STACK: executes both DB_ONLY and NETWORK_ONLY
 */
enum class RequestLevel{
    FULL_STACK, DB_ONLY, NETWORK_ONLY
}