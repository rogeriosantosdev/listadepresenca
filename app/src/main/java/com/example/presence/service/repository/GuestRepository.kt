package com.example.presence.service.repository

import android.content.ContentValues
import android.content.Context
import androidx.core.content.contentValuesOf
import com.example.presence.service.constants.DataBaseConstants
import com.example.presence.service.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {

    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return GuestRepository(context)
        }
    }

    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val insertValues = ContentValues()
            insertValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            insertValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, insertValues)
            true
        } catch (e: Exception){
            false
        }

    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }


    fun update(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val insertValues = ContentValues()
            insertValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            insertValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " * ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, insertValues, selection, args)
            true
        } catch (e: Exception){
            false
        }
    }

    fun delete(guest: GuestModel) {
        
    }
}