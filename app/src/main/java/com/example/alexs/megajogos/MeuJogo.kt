package com.example.alexs.megajogos

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_meu_jogo2.*
import org.json.JSONObject

class MeuJogo : Activity() {

    lateinit var tvNum1 : TextView
    lateinit var tvNum2 : TextView
    lateinit var tvNum3 : TextView
    lateinit var tvNum4 : TextView
    lateinit var tvNum5 : TextView
    lateinit var tvNum6 : TextView
    lateinit var megadao : MegaSenaDAO
    lateinit var txtConcurso : TextView
    lateinit var txtQtdAcertos : TextView
    var a = 0
    var meusNumeros = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meu_jogo2)

        txtQtdAcertos= findViewById(R.id.txtAcertos)
        txtConcurso = findViewById(R.id.txtConcurso)

        tvNum1 = findViewById(R.id.tvNum1)
        tvNum2 = findViewById(R.id.tvNum2)
        tvNum3 = findViewById(R.id.tvNum3)
        tvNum4 = findViewById(R.id.tvNum4)
        tvNum5 = findViewById(R.id.tvNum5)
        tvNum6 = findViewById(R.id.tvNum6)


        var it = intent
        var js = JSONObject(it.getStringExtra("concursoJson"))
        Log.i("jogoRecebido",js.toString())
        Log.i("numJogo", it.getStringExtra("meuJogo"))

        this.megadao = MegaSenaDAO(this)
        for(m : MegaSena in megadao.select()){
            if(m.jogo.toString() == it.getStringExtra("meuJogo")) {
                meusNumeros.add(m.numero1.toString())
                meusNumeros.add(m.numero2.toString())
                meusNumeros.add(m.numero3.toString())
                meusNumeros.add(m.numero4.toString())
                meusNumeros.add(m.numero5.toString())
                meusNumeros.add(m.numero6.toString())

                    povoa(meusNumeros)
                var b = 0
                for(n : String in meusNumeros){
                        if(meusNumeros.contains(js.getJSONArray("sorteio").get(b).toString())){
                            Log.i("verificando",js.getJSONArray("sorteio").get(b).toString() + " - " +n.toString())
                            a++
                        }
                    b++
                }

                txtQtdAcertos.text = a.toString()
                txtConcurso.text = js.getString("numero").toString()
                Log.i("numeros", meusNumeros.toString())
            }
        }

    }

    fun povoa(nums : ArrayList<String>){
        tvNum1.text = nums.get(0)
        tvNum2.text = nums.get(1)
        tvNum3.text = nums.get(2)
        tvNum4.text = nums.get(3)
        tvNum5.text = nums.get(4)
        tvNum6.text = nums.get(5)
    }
}
