package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.intent.databinding.ActivityMainBinding

const val nama:String="1"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendButton.setOnClickListener(){
            if (binding.inputEditText.text.toString().isEmpty()){
                Toast.makeText(this,"Kosong",Toast.LENGTH_SHORT).show()
            }
            else{
                var intent=Intent(this,MainActivity2::class.java)
                intent.putExtra(nama,binding.inputEditText.text.toString())
                startActivity(intent)
            }
        }
    }
}