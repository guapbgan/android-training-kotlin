package com.example.demo2_kotlin_jar

import android.view.View
import com.google.android.material.snackbar.Snackbar

class Foo : View.OnClickListener {
    override fun onClick(p0: View?) {
        Snackbar.make(p0!!, "button3 from outside Foo", Snackbar.LENGTH_LONG).show()
    }

}
