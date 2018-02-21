    package com.example.alexs.megajogos

    import android.app.Activity
    import android.content.Intent
    import android.os.AsyncTask
    import android.os.Bundle
    import android.util.Log
    import android.widget.Button
    import android.widget.Toast
    import org.json.JSONArray
    import org.json.JSONException
    import org.json.JSONObject
    import java.io.BufferedReader
    import java.io.IOException
    import java.io.InputStreamReader
    import java.net.HttpURLConnection
    import java.net.MalformedURLException
    import java.net.URL

    class MainActivity : Activity() {

        lateinit var btnComecar : Button
        lateinit var megadao : MegaSenaDAO

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            btnComecar = findViewById(R.id.btnComecar)

            btnComecar.setOnClickListener({onClick()})

//            Teste Inserção no Banco
            val m = MegaSena(
                    1111,
                    1,
                    2,
                    3,
                    4,
                    5,
                    6
            )

//            megadao = MegaSenaDAO(this)
//            megadao.insert(m)
//        megadao.delete(1112)
//            Log.i("testeJogoMega", megadao.select().toString())

        }

        fun onClick(){
            try {

                var download = DownJson()
                download.execute("https://www.lotodicas.com.br/api/mega-sena")

            }catch (e : Exception){
                Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show()
            }
        }

        inner class DownJson : AsyncTask<String, Void, String>()  {
            override fun doInBackground(vararg p0: String): String? {
                var connection : HttpURLConnection? = null
                var reader : BufferedReader? = null
                try {
                    val url2 = URL(p0.get(0))
                    Log.i("msg",url2.toString())
                    connection = url2.openConnection() as HttpURLConnection
                    Log.i("Aqui","Chegou Aqui!")
                    connection.connect()

                    val stream = connection.inputStream
                    reader = BufferedReader(InputStreamReader(stream))
                    val buffer = StringBuffer()
                    var line = reader.readLine()

                    buffer.append(line)

                    return buffer.toString()

                }
                catch (e : MalformedURLException)
                {
                    e.printStackTrace();
                } catch (e : IOException)
                {
                    e.printStackTrace();
                } catch (e : JSONException)
                {
                    e.printStackTrace();
                } finally
                {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (e : IOException) {
                        e.printStackTrace();
                    }
                }
             return null
        }

            override fun onPostExecute(line : String?) {

                    if(line == null){
                        Log.i("Loto","Nao tem result")
                    }else{
                        var megaJason : JSONObject = JSONObject(line)
                        var concurso : String = megaJason.getString("numero")
                        var sorteio : JSONArray? = megaJason.getJSONArray("sorteio")
        //                Log.i("LotoUrl",url.toString())

                            var it = Intent(this@MainActivity,ResultadoAtual::class.java)
                            it.putExtra("consurso",concurso)
                            it.putExtra("json", megaJason.toString())
                            Log.i("opcao","Mega")
                            startActivity(it)
                        }
            }
        }
    }

