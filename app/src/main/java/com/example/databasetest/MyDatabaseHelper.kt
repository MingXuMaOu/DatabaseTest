package com.example.databasetest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context,name: String,version: Int): SQLiteOpenHelper(context,name,null,version) {

    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"

    private val createCategory = "create table Category (" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(createBook)
        p0?.execSQL(createCategory)
        Toast.makeText(context,"Created succeded",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists Book")
        p0?.execSQL("drop table if exists Category")
        onCreate(p0)
    }

}