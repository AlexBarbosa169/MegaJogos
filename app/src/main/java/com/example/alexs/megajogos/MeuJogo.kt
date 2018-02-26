package com.example.alexs.megajogos

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
    lateinit var tvExibePrem : TextView
    lateinit var btnApaga : Button
    lateinit var megadao : MegaSenaDAO
    lateinit var txtConcurso : TextView
    lateinit var txtQtdAcertos : TextView
    var a = 0
    var meusNumeros = arrayListOf<String>()
    var numerosImpressos = arrayListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meu_jogo2)

        txtQtdAcertos= findViewById(R.id.txtAcertos)
        txtConcurso = findViewById(R.id.txtConcurso)
        btnApaga = findViewById(R.id.btnApagar)
        tvExibePrem = findViewById(R.id.txtExibePremiado)
        numerosImpressos.add(findViewById(R.id.tvNum1))
        numerosImpressos.add(findViewById(R.id.tvNum2))
        numerosImpressos.add(findViewById(R.id.tvNum3))
        numerosImpressos.add(findViewById(R.id.tvNum4))
        numerosImpressos.add(findViewById(R.id.tvNum5))
        numerosImpressos.add(findViewById(R.id.tvNum6))



        var it = intent
        var js = JSONObject(it.getStringExtra("concursoJson"))
        Log.i("jogoRecebido",js.toString())
        Log.i("numJogo", it.getStringExtra("meuJogo"))
        var f = it.getStringExtra("meuJogo").toString()

        btnApaga.setOnClickListener(({apaga(f)}))

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
                            a++
                        }
                    b++
                }

                txtQtdAcertos.text = a.toString()
                txtConcurso.text = js.getString("numero").toString()

                if (a < 4){
                    tvExibePrem.text = "Não Premiado"
                }else{
                    tvExibePrem.setBackgroundColor(android.graphics.Color.GREEN)
                    tvExibePrem.text = "Parabéns!!!"
                }
                Log.i("numeros", meusNumeros.toString())
            }
        }

    }

    fun povoa(nums : ArrayList<String>){
        for (i : Int in 0 .. 5){
            numerosImpressos[i].text = nums.get(i)
        }
    }

    fun apaga(jogo : String){
        megadao.delete(Integer.parseInt(jogo))
        this.finish()
    }
}
