package com.development.graduationproject.Service

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.development.graduationproject.Model.UserModel

class DBHelper(val context: Context) :
    SQLiteOpenHelper(context, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION) {
    private val TABLE_NAME = "user"
    private var COL_ID = "id"
    private var COL_NAME = "name"
    private var COL_SURNAME = "surname"
    private var COL_USERNAME = "username"
    private var COL_PASSWORD = "password"
    private var COL_MAIL = "mail"
    private var COL_USERTYPE = "userType"

    companion object {
        private val DATABASE_NAME = "allusers"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME  " +
                    "VARCHAR(256),$COL_SURNAME  VARCHAR(256),$COL_USERNAME  VARCHAR(256),$COL_PASSWORD  VARCHAR(256)," +
                    "$COL_MAIL  VARCHAR(256),$COL_USERTYPE  VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(userModel: UserModel) {

        val sqLiteDB = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_NAME, userModel.name)
        contentValues.put(COL_SURNAME, userModel.surname)
        contentValues.put(COL_USERNAME, userModel.username)
        contentValues.put(COL_PASSWORD, userModel.password)
        contentValues.put(COL_MAIL, userModel.mail)
        contentValues.put(COL_USERTYPE, userModel.userType)

        val result = sqLiteDB.insert(TABLE_NAME, null, contentValues)
        Toast.makeText(
            context,
            if (result != -1L) "Kayıt Başarılı" else "Kayıt yapılamadı.",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun readData(): MutableList<UserModel> {
        val userList = mutableListOf<UserModel>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val user = UserModel()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.surname = result.getString(result.getColumnIndex(COL_SURNAME))
                user.username = result.getString(result.getColumnIndex(COL_USERNAME))
                user.password = result.getString(result.getColumnIndex(COL_PASSWORD))
                user.mail = result.getString(result.getColumnIndex(COL_MAIL))
                user.userType = result.getString(result.getColumnIndex(COL_USERTYPE))
                userList.add(user)
            } while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return userList
    }


}