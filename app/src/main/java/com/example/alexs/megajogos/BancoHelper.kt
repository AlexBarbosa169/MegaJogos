package com.example.alexs.megajogos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val VERSAO = 1

class BancoHelper(context: Context?) : SQLiteOpenHelper(context, "megaSena.sql3", null, VERSAO) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table apostaMega(jogo integer primary key autoincrement, " +
                "numero1 integer, " +
                "numero2 integer," +
                "numero3 integer," +
                "numero4 integer," +
                "numero5 integer," +
                "numero6 integer);"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table apostaMega")
        this.onCreate(db)
    }

}