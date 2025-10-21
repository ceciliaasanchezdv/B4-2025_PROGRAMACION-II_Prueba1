package com.csanchez.p1_restaurant.clases_modelo

class CuentaMesa(private val mesa: Int) //número de mesa/id de la mesa
{
    val _items = mutableListOf<ItemMesa>()
    var aceptaPropina: Boolean = true //por defecto, se acepta propina

    //agregar usando ItemMenu y cantidad
    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        _items.add(ItemMesa(itemMenu, cantidad))
    }

    //agregar directamente un ItemMesa
    fun agregarItem(itemMesa: ItemMesa) {
        _items.add(itemMesa)
    }

    //it hace referencia a cada objeto de ItemMesa contenido en la lista _items y llama a la fun calcularSubtotal()
    fun calcularTotalSinPropina(): Int {
        return _items.sumOf { it.calcularSubtotal() }
    }

    //calcular solo la propina (10%)
    fun calcularPropina(): Int {
        return if (aceptaPropina) {
             (calcularTotalSinPropina() * 0.1).toInt()
        } else {
            0
        }
    }

    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
}
