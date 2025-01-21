package com.example.sharedpreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedpreference.databinding.ActivityMainBinding

const val nama:String="1"
const val sp:String="nama"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sharedPreferences=getSharedPreferences(sp,MODE_PRIVATE)
        binding.saveButton.setOnClickListener(){
            if (binding.inputEditText.text.toString().isEmpty()){
                Toast.makeText(this,"Kosong",Toast.LENGTH_SHORT).show()
            }
            else{
                var edit=sharedPreferences.edit()
                edit.putString(nama,binding.inputEditText.text.toString())
                edit.apply()
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
            }
        }

        binding.loadButton.setOnClickListener(){
            var getEdit=sharedPreferences.getString(nama,null)
            binding.displayTextView.text=getEdit.toString()
            var intent=Intent(this,MainActivity2::class.java)
            intent.putExtra(nama,getEdit.toString())
            startActivity(intent)
        }
    }


}