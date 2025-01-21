package com.example.intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intent.MainActivity2
import com.example.intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Deklarasi binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil nilai dari inputan dan button menggunakan binding
        val nama = binding.nama
        val nim = binding.nim
        val submit = binding.submit

        // Set OnClickListener pada button submit
        submit.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("nama", nama.text.toString())
            intent.putExtra("nim", nim.text.toString())
            startActivity(intent)
        }
    }
}
