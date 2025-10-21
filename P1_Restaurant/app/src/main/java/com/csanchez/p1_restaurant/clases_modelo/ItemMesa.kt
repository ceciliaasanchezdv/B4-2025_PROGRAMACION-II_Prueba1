package com.csanchez.p1_restaurant.clases_modelo

data class ItemMesa(val itemMenu : ItemMenu, var cantidad: Int) { //itemMenu guarda el plato
    fun calcularSubtotal(): Int {
        return itemMenu.precio.toInt() * cantidad //multiplica el precio del plato por la cantidad
    }
}