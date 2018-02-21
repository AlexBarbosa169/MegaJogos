package com.example.alexs.megajogos

/**
 * Created by alexs on 10/02/2018.
 */

class MegaSena {
    var jogo : Int
    var numero1 : Int = 0
    var numero2 : Int = 0
    var numero3 : Int = 0
    var numero4 : Int = 0
    var numero5 : Int = 0
    var numero6 : Int = 0


    constructor(jogo: Int, numeros: ArrayList<Int>) {
        this.jogo = jogo
        var i : Int = 0
        for(n in numeros){
            this.setNumero(n,i)
            i++
        }
    }

    constructor(jogo: Int, numero1: Int, numero2: Int, numero3: Int, numero4: Int, numero5: Int, numero6: Int) {
        this.jogo = jogo
        this.numero1 = numero1
        this.numero2 = numero2
        this.numero3 = numero3
        this.numero4 = numero4
        this.numero5 = numero5
        this.numero6 = numero6
    }

    fun setNumero(x: Int, y : Int){
        when (x) {
            1 -> this.numero1 = y
            2 -> this.numero2 = y
            3 -> this.numero3 = y
            4 -> this.numero4 = y
            5 -> this.numero5 = y
            6 -> this.numero6 = y
            else -> { // Note the block
                print("Erro!")
            }
        }
    }

//    override fun toString(): String {
//        return "ApostaMega(jogo=$jogo, numero1=$numero1, numero2=$numero2, numero3=$numero3, numero4=$numero4, numero5=$numero5, numero6=$numero6)"
//    }

    override fun toString(): String {
        return "jogo = $jogo [ $numero1, $numero2, $numero3, $numero4, $numero5, $numero6 ]"
    }


}