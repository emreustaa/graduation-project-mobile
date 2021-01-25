package com.development.graduationproject.Service

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.development.graduationproject.Model.ProjectModel
import com.development.graduationproject.Model.UserModel

class ProjectsDB(val context: Context) :
    SQLiteOpenHelper(context, ProjectsDB.NAME, null, ProjectsDB.VERSION) {
    private val TABLE_NAME = "project"
    private var COL_ID = "id"
    private var COL_PROJECT_NAME = "projectName"
    private var COL_DETAIL = "detail"
    private var COL_DATE_TIME = "datetime"
    private var COL_REQUEST_AMOUNT = "amount"
    private var COL_NUMBER_PEOPLE = "people"
    private var COL_CATEGORY = "category"
    private var COL_SECTOR = "sector"
    private var COL_OWNER = "owner"


    companion object {
        private val NAME = "allprojects"
        private val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_PROJECT_NAME  " +
                    "VARCHAR(256),$COL_DETAIL  VARCHAR(256),$COL_DATE_TIME  VARCHAR(256)," +
                    "$COL_REQUEST_AMOUNT  VARCHAR(256),$COL_NUMBER_PEOPLE  INT," +
                    "$COL_CATEGORY  VARCHAR(256),$COL_SECTOR  VARCHAR(256)," +
                    "$COL_OWNER  VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(projectModel: ProjectModel) {

        val sqLiteDB = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_PROJECT_NAME, projectModel.projectName)
        contentValues.put(COL_DETAIL, projectModel.detail)
        contentValues.put(COL_DATE_TIME, projectModel.dateTime)
        contentValues.put(COL_REQUEST_AMOUNT, projectModel.requestAmount)
        contentValues.put(COL_NUMBER_PEOPLE, projectModel.numberOfPeople)
        contentValues.put(COL_CATEGORY, projectModel.category)
        contentValues.put(COL_SECTOR, projectModel.sector)
        contentValues.put(COL_OWNER, projectModel.owner)


        val result = sqLiteDB.insert(TABLE_NAME, null, contentValues)
        Toast.makeText(
            context,
            if (result != -1L) "Kayıt Başarılı" else "Kayıt yapılamadı.",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun readData(): MutableList<ProjectModel> {
        val projectList = mutableListOf<ProjectModel>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val project = ProjectModel()
                project.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                project.projectName = result.getString(result.getColumnIndex(COL_PROJECT_NAME))
                project.detail = result.getString(result.getColumnIndex(COL_DETAIL))
                project.dateTime = result.getString(result.getColumnIndex(COL_DATE_TIME))
                project.requestAmount = result.getString(result.getColumnIndex(COL_REQUEST_AMOUNT))
                project.numberOfPeople =
                    result.getString(result.getColumnIndex(COL_NUMBER_PEOPLE)).toInt()
                project.category = result.getString(result.getColumnIndex(COL_CATEGORY))
                project.sector = result.getString(result.getColumnIndex(COL_SECTOR))
                project.owner = result.getString(result.getColumnIndex(COL_OWNER))

                projectList.add(project)
            } while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return projectList
    }

}