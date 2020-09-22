package com.example.demo2_kotlin_jar

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.demo2_kotlin_jar.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import kotlin.collections.Map as Map1

class MainActivity : AppCompatActivity(), DialogInterface.OnClickListener {
    companion object{
        const val PHONE_PERMISSION_CHECK = 4321
    }
    lateinit var binding:ActivityMainBinding //lateinit 之後初始化，所以可以不用加? 或指定null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //form getLayoutInflater(), kotlin 可能會自動轉換getXXX成xxx
        setContentView(binding.root)

        val dayOfWeek = resources.getStringArray(R.array.dayOfWeek)
        val builder = StringBuilder()
        for(s in dayOfWeek){
            builder.append(s)
            builder.append("\n")
        }
        textView1.text = builder.toString()
//        binding.textView1.text =

//        setContentView(R.layout.activity_main)
    }
    fun doActivity(v: View){
        val intent = Intent()
        intent.setClass(this, Second::class.java) //因為kotlin目前還不完整，所以項此行是由Java implement 出來的，因此第二個參數要帶入 Second::class.java
        startActivity(intent)

    }

    fun doYahoo(v: View){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("http://www.yahoo.com.tw"))
        startActivity(intent)
    }

    fun doCall(v:View){
        checkPermission()
        callPhone()
    }

    private fun checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            showPromptDialog()
        }else{
            askForPermission()
        }
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PHONE_PERMISSION_CHECK)
    }

    private fun showPromptDialog() {
        AlertDialog.Builder(this).setTitle("need permission from lotlin")
            .setMessage("need call phone permission")
            .setPositiveButton("OK", this)
            .setNegativeButton("No", this)
            .show()
    }

    private fun callPhone(){
        val intent = Intent(ACTION_CALL)
        val uri = Uri.parse("tel:021234567")
        intent.setData(uri)
        try {
            startActivity(intent)
        }catch (e:SecurityException){
//            textView1.text = "need to grant permission to call phone"
            binding.textView1.text = "need to grant permission to call phone"
        }

    }

    override fun onClick(p0: DialogInterface?, p1: Int) {
        when(p1){
            DialogInterface.BUTTON_NEGATIVE -> finish()
            DialogInterface.BUTTON_POSITIVE -> askForPermission()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray
    ) {
        when(requestCode){
            PHONE_PERMISSION_CHECK->
                if(grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    callPhone()
                }else{
//                    textView1.text = "try again"
                    binding.textView1.text = "try again"

                }
        }
    }
}