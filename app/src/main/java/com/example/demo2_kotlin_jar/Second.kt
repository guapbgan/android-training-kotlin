package com.example.demo2_kotlin_jar

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.second.*

class Second : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)
        button1.setOnClickListener(this)

        button2.setOnClickListener(this)

        button3.setOnClickListener(Foo())

        button4.setOnClickListener(Bar())

        button5.setOnClickListener({v->
            Snackbar.make(v, "button5 clicked", Snackbar.LENGTH_LONG).show() //大括號對kotlin的意義即為一段程式碼
        })

        button6.setOnClickListener(){v->
            Snackbar.make(v, "button6 clicked", Snackbar.LENGTH_LONG).show() //大括弧可以移到外面
        }

        button7.setOnClickListener {v->
            Snackbar.make(v, "button7 clicked", Snackbar.LENGTH_LONG).show() //小括弧可以不用
        }

        button8.setOnClickListener {
            Snackbar.make(button8, "button8 clicked", Snackbar.LENGTH_LONG).show() //v可以不用
        }

        button9.setOnClickListener{run {Snackbar.make(button9, "button9 clicked", Snackbar.LENGTH_LONG)}} //內部run 一段大括號括住的程式碼
    }

    inner class Bar : View.OnClickListener { //old version kotlin need "inner"
        override fun onClick(p0: View?) {
            Snackbar.make(p0!!, "inner class Bar clicked", Snackbar.LENGTH_LONG)
        }

    }


    override fun onClick(p0: View?) {//p0: View? ?為option意思
        var message = ""
        when(p0!!.id){
            R.id.button1-> message = "button1 clicked"
            R.id.button2-> message = "button2 clicked"
        }
        Snackbar.make(p0!!, message, Snackbar.LENGTH_LONG).show() // p0!! !!為保證會有值之意

    }
}