package uk.co.diegobarle.core.model.entity

import androidx.room.TypeConverter

/**
 * Helper class to hold a list of int ids in a String used for storing in database
 */
data class IdList(val ids: List<Int>){
    object Converter{
        @TypeConverter
        @JvmStatic
        fun toString(itemList: IdList?): String? {
            if(itemList == null) return null
            val listStr = StringBuilder()
            for(id in itemList.ids){
                listStr.append(id).append(",")
            }
            return listStr.toString().removeSuffix(",")
        }

        @TypeConverter
        @JvmStatic
        fun toType(listStr: String?): IdList? {
            if(listStr.isNullOrBlank()) return null
            val list = ArrayList<Int>(listStr.split(",").map { Integer.parseInt(it) })
            return IdList(list)
        }
    }
}