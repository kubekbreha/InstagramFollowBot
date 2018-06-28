package com.kubekbreha.instagrambot.util

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.kubekbreha.instagrambot.UsersList


val DATABASE_NAME = "InstagramBotDatabase"
val TABLE_NAME = "Lists"
val COL_ID = "id"
val COL_NAME = "name"
val COL_LIST = "users"

class DatabaseHandlerLists(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_LIST + " TEXT)"

        db?.execSQL(createTable)

    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun updateTask(list: UsersList) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, list.name)
        values.put(COL_LIST, list.list)
        val _success = db.update(TABLE_NAME, values, "Id" + "=?", arrayOf(list.id.toString())).toLong()
        db.close()
        if (_success == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }


    fun insertData(list: UsersList) {
        val db = this.writableDatabase
        val cv = ContentValues()

        //name
        cv.put(COL_NAME, list.name)
        cv.put(COL_LIST, list.list)


        val result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun readData(): MutableList<UsersList> {
        val list: MutableList<UsersList> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var usersList = UsersList()
                usersList.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                usersList.name = result.getString(result.getColumnIndex(COL_NAME))
                usersList.list = result.getString(result.getColumnIndex(COL_LIST))
                list.add(usersList)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun readOneListData(listId: Int): UsersList? {
        val list: MutableList<UsersList> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)

        result.moveToPosition(listId)
        var usersList: UsersList? = null

        if(result != null) {
            usersList = UsersList()
            usersList.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
            usersList.name = result.getString(result.getColumnIndex(COL_NAME))
            usersList.list = result.getString(result.getColumnIndex(COL_LIST))
        }

        result.close()
        db.close()
        return usersList
    }

    fun deleteData() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }
}
