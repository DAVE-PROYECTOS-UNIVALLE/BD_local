package com.example.bd_locales

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context): SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME="inventario.db"
        private const val DATABASE_VERSION=1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val crearCategoria="""
            CREATE TABLE categoria(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(crearCategoria)
        val crearProducto = """
            CREATE TABLE producto(
                codigo TEXT PRIMARY KEY,
                nombre TEXT NOT NULL,
                precio INTEGER NOT NULL,
                cantidad INTEGER NOT NULL,
                id_categoria INTEGER NOT NULL,
                FOREIGN KEY (id_categoria) REFERENCES categoria(id) ON DELETE CASCADE
            )
        """.trimIndent()
        db?.execSQL(crearProducto)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS categoria")
        db?.execSQL("DROP TABLE IF EXISTS producto")
        onCreate(db)
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        if (!db?.isReadOnly!!){
            db?.execSQL("PRAGMA foreign_keys=ON")
        }
    }
}