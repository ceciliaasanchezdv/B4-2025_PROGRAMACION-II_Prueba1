package com.csanchez.p1_restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    //Precios de los platos
    var precioPastel = 12000
    var precioCazuela = 10000

    //Vistas (elementos del XML)
    lateinit var etPastel: EditText
    lateinit var etCazuela: EditText
    lateinit var swPropina: Switch
    lateinit var tvComida: TextView
    lateinit var tvPropina: TextView
    lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Conectar vistas del XML con el código
        etPastel = findViewById(R.id.etPastel)
        etCazuela = findViewById(R.id.etCazuela)
        swPropina = findViewById(R.id.swPropina)
        tvComida = findViewById(R.id.tvComida)
        tvPropina = findViewById(R.id.tvPropina)
        tvTotal = findViewById(R.id.tvTotal)

        //Listener cuando se cambia el texto en los EditText
        etPastel.addTextChangedListener(textWatcher)
        etCazuela.addTextChangedListener(textWatcher)

        //Listener cuando se activa o desactiva el Switch
        swPropina.setOnCheckedChangeListener { _, _ ->
            calcularTotales()
        }

        //Mostrar totales iniciales (todo en $0)
        calcularTotales()
    }

    //Detecta cambios en los EditText
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            calcularTotales()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    //Función que hace los cálculos
    fun calcularTotales() {
        //Obtiene las cantidades o usa 0 si el campo está vacío
        val cantPastel = etPastel.text.toString().toIntOrNull() ?: 0
        val cantCazuela = etCazuela.text.toString().toIntOrNull() ?: 0

        //Calcula el subtotal y la propina
        val subtotal = (cantPastel * precioPastel) + (cantCazuela * precioCazuela)
        val propina = if (swPropina.isChecked) (subtotal * 0.1).toInt() else 0
        val total = subtotal + propina

        //Formatea los valores a pesos chilenos (sin decimales)
        val formatoCLP = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        formatoCLP.maximumFractionDigits = 0

        //Muestra los resultados en pantalla
        tvComida.text = "Comida: ${formatoCLP.format(subtotal)}"
        tvPropina.text = "Propina: ${formatoCLP.format(propina)}"
        tvTotal.text = "TOTAL: ${formatoCLP.format(total)}"
    }
}