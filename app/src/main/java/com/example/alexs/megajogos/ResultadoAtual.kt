package com.example.alexs.megajogos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject

class ResultadoAtual : Activity() {

    lateinit var tvConcurso : TextView
    lateinit var tvNum1 : TextView
    lateinit var tvNum2 : TextView
    lateinit var tvNum3 : TextView
    lateinit var tvNum4 : TextView
    lateinit var tvNum5 : TextView
    lateinit var tvNum6 : TextView
    lateinit var tvSena : TextView
    lateinit var tvQuina : TextView
    lateinit var tvQuadra : TextView
    lateinit var tvPremioSena : TextView
    lateinit var tvPremioQuina : TextView
    lateinit var tvPremioQuadra : TextView
    lateinit var btnDetalhes : Button
    lateinit var btnMeusJogos : Button
    lateinit var resultado : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_atual)

        tvConcurso = findViewById(R.id.tvConcursoNum)
        tvNum1 = findViewById(R.id.tvNum1)
        tvNum2 = findViewById(R.id.tvNum2)
        tvNum3 = findViewById(R.id.tvNum3)
        tvNum4 = findViewById(R.id.tvNum4)
        tvNum5 = findViewById(R.id.tvNum5)
        tvNum6 = findViewById(R.id.tvNum6)
        tvSena = findViewById(R.id.tvSena)
        tvQuina = findViewById(R.id.tvQuina)
        tvQuadra = findViewById(R.id.tvQuadra)
        tvPremioSena = findViewById(R.id.tvPremioSena)
        tvPremioQuina = findViewById(R.id.tvPremioQuina)
        tvPremioQuadra = findViewById(R.id.tvPremioQuadra)
        btnMeusJogos = findViewById(R.id.btnMeusJogos)

        var it = intent
        resultado = it.getStringExtra("json")
        var js = JSONObject(resultado)

//        btnDetalhes.setOnClickListener({onclickDetalhes(it)})
        btnMeusJogos.setOnClickListener({onMeusJogos()})

        Log.i("jsteste",resultado)

        tvConcurso.text = js.getString("numero").toString()

        tvNum1.text = js.getJSONArray("sorteio").get(0).toString()
        tvNum2.text = js.getJSONArray("sorteio").get(1).toString()
        tvNum3.text = js.getJSONArray("sorteio").get(2).toString()
        tvNum4.text = js.getJSONArray("sorteio").get(3).toString()
        tvNum5.text = js.getJSONArray("sorteio").get(4).toString()
        tvNum6.text = js.getJSONArray("sorteio").get(5).toString()
        tvSena.text = js.getJSONArray("ganhadores").get(0).toString()
        tvQuina.text = js.getJSONArray("ganhadores").get(1).toString()
        tvQuadra.text = js.getJSONArray("ganhadores").get(2).toString()
        tvPremioSena.text = js.getJSONArray("rateio").get(0).toString()
        tvPremioQuina.text = js.getJSONArray("rateio").get(1).toString()
        tvPremioQuadra.text = js.getJSONArray("rateio").get(2).toString()

    }

    private fun onMeusJogos() {

        var it2 = Intent(this@ResultadoAtual,MeusJogosActivity::class.java)
        it2.putExtra("resultado", resultado)
        startActivity(it2)
    }

}

