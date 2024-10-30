package com.example.bd_locales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var categoria :Cat_interfaz
    private lateinit var tvCategoria : TextView
    private lateinit var btnAgregar : Button
    private lateinit var btnDelete : Button
    private lateinit var listaViewCategorias : ListView
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var entrada: EditText
    private val listaCategorias = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val dbHelper = DBHelper(this)
        tvCategoria = findViewById(R.id.tv1)

        categoria=Categoria_Controlador(dbHelper)

        entrada = findViewById(R.id.edTNombre)
        btnAgregar = findViewById(R.id.btnAg)
        btnDelete  = findViewById(R.id.btnDel)
        listaViewCategorias = findViewById(R.id.listView)

        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            listaCategorias)
        listaViewCategorias.adapter= adapter
        listaViewCategorias.choiceMode= ListView.CHOICE_MODE_SINGLE
        cargarCategorias()

        btnAgregar.setOnClickListener {
            val nombreCategoria = entrada.text.toString().trim()
            if (nombreCategoria.isNotEmpty()){
                val nuevaCategoria = Categoria(
                                    nombre=nombreCategoria)
                categoria.agregarCategoria(nuevaCategoria)
                Log.i("Creacion de Categoria",
                    "BD con categoria creada ${nombreCategoria}")
                entrada.text.clear()
                cargarCategorias()
            }
        }
/*
        // ejemplo de creacion de categoria
        val nuevaCategoria = Categoria(nombre = "Bebidas")
        categoria.agregarCategoria(nuevaCategoria)
        Log.i("tag DB", "Categoria creada")
        // obtener categorias
        val categorias = categoria.obtenerCategorias()
        val categoriaString = StringBuilder()
        for (categoria  in categorias){
            categoriaString.append(
                "ID: ${categoria.id}, " +
                        "Nombre: ${categoria.nombre}\n")
            Log.i("Lista DB",
                "ID: ${categoria.id}, " +
                    "Nombre: ${categoria.nombre}\n")
        }
        tvCategoria.text= categoriaString.toString()

        // actualizar categoria
        if(categorias.isNotEmpty())
        {
            categorias[0].nombre= "Refrescos"
            categoria.actualizarCategoria(categorias[0])
            Log.i("tag actualizacion",
                "bd actualizada")
            tvCategoria.text= categorias[0].nombre
        }
        // eliminar categoria
        if (categorias.isNotEmpty())
        {
            categoria.eliminarCategoria(categorias[0].id)
            Log.i("eliminar datos",
                "bd actualizada")
        }*/

    }
    private fun cargarCategorias(){
        val categorias= categoria.obtenerCategorias()
        listaCategorias.clear()

        for (cat in categorias)
        {
            listaCategorias.add(
                "ID: ${cat.id}, Nombre: ${cat.nombre}")
            Log.i("listar categorias",
                "ID: ${cat.id}, Nombre: ${cat.nombre}")
        }
        adapter.notifyDataSetChanged()
    }
}