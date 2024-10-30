package com.example.bd_locales

interface Cat_interfaz {
    fun agregarCategoria(categoria : Categoria)
    fun obtenerCategorias(): List<Categoria>
    fun obtenerCategoriaPorId(id : Int):Categoria?
    fun actualizarCategoria(categoria: Categoria): Int
    fun eliminarCategoria(id: Int): Int
}