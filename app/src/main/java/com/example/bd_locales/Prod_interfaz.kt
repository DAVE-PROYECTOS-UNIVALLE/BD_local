package com.example.bd_locales

interface Prod_interfaz {
    fun agregarProducto(producto: Producto)
    fun obtenerProductos(): List<Producto>
    fun obtenerProductoPorId(id : String):Producto?
    fun actualizarProducto(producto: Producto): Int
    fun eliminarProducto(id: String): Int
}