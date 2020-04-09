package uk.co.diegobarle.core.model.dao

import androidx.room.*

/**
 * Defines basic interface for an entity dao
 */
@Dao
interface BaseEntityDao<T>{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(values: List<T>)

    @Update
    fun update(location: T): Int

    @Delete
    fun delete(values: T)

    @Delete
    fun delete(values: List<T>)
}