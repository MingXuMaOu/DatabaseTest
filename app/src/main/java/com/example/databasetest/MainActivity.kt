package com.example.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = MyDatabaseHelper(this,"BookStore.db",2)
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

        addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("name","The Da Vinci Code")
                put("author","Dan Brown")
                put("pages",454)
                put("price",16.96)
            }
            db.insert("Book",null,values1)

            val values2 = ContentValues().apply {
                put("name","The Lost Symbol")
                put("author","Dan Brown")
                put("pages",510)
                put("price",19.95)
            }
            db.insert("Book",null,values2)
        }

        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price",10.99)
            db.update("Book",values,"name=?", arrayOf("The Da Vinci Code"))
        }

        deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book","pages > ?", arrayOf("500"))
        }

        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book",null,null,null,null,null,null)
            if(cursor.moveToFirst()){
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getString(cursor.getColumnIndex("pages"))
                    val price = cursor.getString(cursor.getColumnIndex("price"))
                    Log.d("MainActivity","$name $author $pages $price")
                }while (cursor.moveToNext())
            }
            cursor.close()
        }
    }
}