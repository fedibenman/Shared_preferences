package com.example.shared_preferences

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {
    lateinit var btnSave: Button
    lateinit var btnload: Button
    lateinit var mEditText:EditText
    val File_Name:String = "example.txt" ;
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnload = findViewById(R.id.load_btn)
        btnSave = findViewById(R.id.save_btn)
        mEditText = findViewById(R.id.mEditText)
        val sharedPreference:SharedPreference=SharedPreference(this)

        btnSave.setOnClickListener {
save()
        }


        btnload.setOnClickListener{
load()
        }

    }



    fun save(){

        val text: String = mEditText.getText().toString()
        var fo: FileOutputStream? = null
        try {
            fo = openFileOutput(File_Name, MODE_PRIVATE)
            fo.write(text.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mEditText.getText().clear()
        Log.i("Saved to", "$filesDir/$File_Name")
        if (fo != null) {
            try {
                fo.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

fun load(){

  val  fis = openFileInput(File_Name)
    val isr = InputStreamReader(fis)
    val br = BufferedReader(isr)
    val sb = StringBuilder()
    var text: String?
    while (br.readLine().also { text = it } != null) {
        sb.append(text).append("\n")
    }
    mEditText.setText(sb.toString())
}


}