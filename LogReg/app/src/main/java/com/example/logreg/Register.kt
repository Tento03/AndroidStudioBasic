package com.example.logreg

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.logreg.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sharedPreferences=getSharedPreferences(sp,Context.MODE_PRIVATE)
        binding.registerButton.setOnClickListener(){
            if (binding.registerEmailEditText.text.toString().isEmpty() && binding.registerPasswordEditText.text.toString().isEmpty() && binding.registerConfirmPasswordEditText.text.toString().isEmpty()){
                Toast.makeText(this,"Harus semua berisi", Toast.LENGTH_SHORT).show()
            }
            else{
                var edit=sharedPreferences.edit()
                edit.putString(email,binding.registerEmailEditText.text.toString())
                edit.putString(pass,binding.registerPasswordEditText.text.toString())
                edit.apply()

                var getEditEmail=sharedPreferences.getString(email,"emai")
                var getEditPass=sharedPreferences.getString(pass,"pass")

                var intent=Intent(this,Login::class.java)
                intent.putExtra(email,getEditEmail)
                intent.putExtra(pass,getEditPass)
                startActivity(intent)
            }
        }
        binding.loginLink.setOnClickListener(){
            var intent= Intent(this,Login::class.java)
            startActivity(intent)
        }
    }
}