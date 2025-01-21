package com.example.logreg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.logreg.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailTextView.text=intent.getStringExtra(email).toString()
        binding.passwordTextView.text=intent.getStringExtra(pass).toString()
        binding.logoutButton.setOnClickListener(){
            var intent=Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}