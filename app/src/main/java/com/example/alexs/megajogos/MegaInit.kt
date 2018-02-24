package com.example.alexs.megajogos

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by alexs on 24/02/2018.
 */

class BootIni : BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i("TestBR","Foi!")
        var it = Intent(p0,MainActivity::class.java)
//        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        p0?.startActivity(it)
    }
}