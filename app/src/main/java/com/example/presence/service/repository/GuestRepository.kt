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

    fun get(id: Int): GuestModel? {
        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(DataBaseConstants.GUEST.COLUMNS.NAME, DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME,
            projection,
            selection,
            args,
            null,
            null,
            null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    guest = GuestModel(id, name, presence)
                }

                cursor.close()
                guest
            }

            guest
        } catch (e: Exception){
            guest
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
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

                cursor.close()
                list
            }

            list
        } catch (e: Exception){
            list
        }
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

                cursor.close()
                list
            }

            list
        } catch (e: Exception){
            list
        }
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

                cursor.close()
                list
            }

            list
        } catch (e: Exception){
            list
        }
    }


    fun update(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val insertValues = ContentValues()
            insertValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            insertValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, insertValues, selection, args)
            true
        } catch (e: Exception){
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception){
            false
        }
    }
}