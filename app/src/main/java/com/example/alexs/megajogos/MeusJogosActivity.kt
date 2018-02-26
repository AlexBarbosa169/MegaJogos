package com.example.alexs.megajogos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MeusJogosActivity : Activity() {

    lateinit var lista: ListView
    lateinit var btnAdd: Button
    lateinit var megadao : MegaSenaDAO
    lateinit var resultado : String
    var jogos = hashMapOf<Int,MegaSena>()
//    var nomes = arrayListOf<String>("Thalita", "Gilberto", "Alex", "Júnior", "Lucas", "Raimundo", "Mateus")
    var nomes = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meus_jogos)
//      Testando acesso para leitura do banco

        var it = intent
        resultado = it.getStringExtra("resultado")

        megadao = MegaSenaDAO(this)
        for(m : MegaSena in megadao.select()){
            nomes.add(m.toString())
        }

        Log.i("MeusJogos", resultado)

//      Testando acesso para leitura do banco

        btnAdd = findViewById(R.id.btnInsereJogo)
        lista = findViewById(R.id.lista)

        btnAdd.setOnClickListener({ insere(1) })
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes)
        this.lista.adapter = adapter

        this.lista.setOnItemClickListener({ parent, view, position, id ->
            abreJogo(position)
        })

        // click longo
        this.lista.setOnItemLongClickListener({ parent, view, position, id ->
            remove(position)
            true
        })
        Log.i("ciclo","onCreate")
    }

    /*override fun onStart() {
        super.onStart()
        nomes.clear()
        megadao = MegaSenaDAO(this)
        for(m : MegaSena in megadao.select()){
            nomes.add(m.toString())
        }
        Log.i("ciclo","onStart")
    }*/

    override fun onRestart() {
        super.onRestart()
        nomes.clear()
        megadao = MegaSenaDAO(this)
        for(m : MegaSena in megadao.select()){
            nomes.add(m.toString())
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes)
        this.lista.adapter = adapter
        Log.i("ciclo","onRestart")
    }

//    override fun onResume() {
//        super.onResume()
//
//    }

    fun insere(position : Int){
        var it = Intent(this,NovoJogoActivity::class.java)
        startActivity(it)
    }


    fun remove(position : Int){
        (this.lista.adapter as ArrayAdapter<String>).remove((this.lista.adapter as ArrayAdapter<String>).getItem(position))
    }

    fun abreJogo(position: Int){
        var it2 = Intent(this,MeuJogo::class.java)

        var a = 0
        for(m : MegaSena in megadao.select()){
            nomes.add(m.toString())
            if(a == position){
                it2.putExtra("meuJogo", m.jogo.toString())
                Log.i("position",position.toString())
            }
            a++
        }
        it2.putExtra("concursoJson", this.resultado)
        startActivity(it2)
    }
}
