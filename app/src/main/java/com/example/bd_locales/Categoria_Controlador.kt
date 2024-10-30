package com.example.bd_locales

import android.content.ContentValues

class Categoria_Controlador(
    private val dbHelper: DBHelper):Cat_interfaz {
    override fun agregarCategoria(categoria: Categoria) {
        val db = dbHelper.writableDatabase
        val values= ContentValues().apply {
            put("nombre", categoria.nombre)
        }
        db.insert(
            "categoria",
            null,
            values)
    }

    override fun obtenerCategorias(): List<Categoria> {
        val categorias = mutableListOf<Categoria>()
        val db= dbHelper.readableDatabase
        val cursor= db.query(
            "categoria",
            null,
            null,
            null,
            null,
            null,
            null
        ) // SELECT * FROM CATEGORIA
        with(cursor){
            while(moveToNext()){
                val categoria = Categoria(
                    id= getInt(
                        getColumnIndexOrThrow("id")),
                    nombre = getString(
                        getColumnIndexOrThrow("nombre"))
                )
                categorias.add(categoria)
            }
            close()
        }
        return categorias
    }

    override fun obtenerCategoriaPorId(id: Int): Categoria? {
        val db = dbHelper.readableDatabase
        var categoria : Categoria?=null
        val resultado = db.query(
            "categoria",
            null,
            "id=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        with(resultado){
            if (moveToFirst()){
                categoria = Categoria(
                    id= getInt(
                        getColumnIndexOrThrow("id")),
                    nombre = getString(
                        getColumnIndexOrThrow("nombre"))
                )
            }
            close()
        }
        return categoria
    }

    override fun actualizarCategoria(categoria: Categoria): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", categoria.nombre)
        }
        return db.update(
            "categoria",
            values,
            "id=?",
            arrayOf(categoria.id.toString())
            // update categoria set nombre = "valor"
            //  where id=categoria.id;
        )

    }

    override fun eliminarCategoria(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete(
            "categoria",
            "id=?",
            arrayOf(id.toString())
        )
    }

}