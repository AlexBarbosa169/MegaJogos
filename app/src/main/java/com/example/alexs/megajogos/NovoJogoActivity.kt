package com.example.alexs.megajogos

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.GridLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

class NovoJogoActivity : Activity() {

    lateinit var gridNumeros : GridLayout
    lateinit var checks : ArrayList<CheckBox>
    lateinit var a : CheckBox
    lateinit var btnSalvar : Button
    lateinit var megadao : MegaSenaDAO

    var meusNumeros = ArrayList<String>()
    var numerosSelecionados = hashMapOf<Int,String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_jogo)

//        this.megadao = MegaSenaDAO(this)
        checks = ArrayList()
        btnSalvar = findViewById(R.id.btnSalvar)
        gridNumeros = findViewById(R.id.gdNovoJogo)

//        gridNumeros.setAdapter(gridArrayAdapter)
//        var grid = CheckBox(this)

        btnSalvar.setOnClickListener({salvar()})

        for (i in 1 .. 60){
            a = CheckBox(this)
            a.id = i
            a.text = i.toString()
            a.tag = i.toString()
            a.setOnClickListener({onSelect(it)})
            checks.add(a)
            gridNumeros.addView(a)
        }

        Log.i("numGrid", gridNumeros.getChildAt(1).tag.toString())

    }

    private fun onSelect(v : View){
        var cont : Int = 0
        numerosSelecionados.clear()
        meusNumeros.clear()
        if(a.isChecked){
            a.setBackgroundColor(titleColor)

        }

        for (i in 0 .. 59){
            if((gridNumeros.getChildAt(i) as CheckBox).isChecked){
                numerosSelecionados.put(i, (gridNumeros.getChildAt(i) as CheckBox).getTag().toString())
                meusNumeros.add((gridNumeros.getChildAt(i) as CheckBox).getTag().toString())
                cont++
            }
        }

        Log.i("selecionados", cont.toString())
        Log.i("MeusNumeros", meusNumeros.toString())

        if (cont < 6){
            btnSalvar.visibility = View.INVISIBLE
        }

        if(cont == 6){
            btnSalvar.setBackgroundColor(android.graphics.Color.GREEN)
            btnSalvar.visibility = View.VISIBLE
        }

        if (cont > 6){
            btnSalvar.setBackgroundColor(android.graphics.Color.RED)
        }
    }

    fun salvar(){

        val m = MegaSena(
                -1,
                meusNumeros[0].toInt(),
                meusNumeros[1].toInt(),
                meusNumeros[2].toInt(),
                meusNumeros[3].toInt(),
                meusNumeros[4].toInt(),
                meusNumeros[5].toInt()
        )

            megadao = MegaSenaDAO(this)
            megadao.insert(m)
//        megadao.delete(1112)
            Log.i("testeJogoMega", megadao.select().toString())
            Toast.makeText(this,"Salvo",LENGTH_SHORT).show()
            finish()
    }
}
