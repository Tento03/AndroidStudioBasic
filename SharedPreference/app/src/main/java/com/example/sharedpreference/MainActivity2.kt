package com.example.sharedpreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreference.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var nama=intent.getStringExtra(nama)
        var sharedPreferences=getSharedPreferences(sp,Context.MODE_PRIVATE)
        var getEdit=sharedPreferences.getString(nama,null)
        binding.dataTextView.text=nama.toString()
    }
}