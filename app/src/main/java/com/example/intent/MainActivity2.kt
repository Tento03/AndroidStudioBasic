package com.example.intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intent.MainActivity
import com.example.intent.databinding.ActivityMain2Binding
import com.example.intent.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    // Deklarasi binding
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil nilai dari intent dan set ke TextView menggunakan binding
        binding.hasilNama.text = intent.getStringExtra("nama").toString()
        binding.hasilnim.text = intent.getStringExtra("nim").toString()

        // Set OnClickListener pada tombol kembali
        binding.back.setOnClickListener {
            finish() // Menutup activity ini dan kembali ke activity sebelumnya
        }
    }
}
