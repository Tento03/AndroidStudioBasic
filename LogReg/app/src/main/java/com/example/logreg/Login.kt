package com.example.logreg

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.logreg.databinding.ActivityLoginBinding

const val email:String="1"
const val pass:String="2"
const val sp:String="3"
class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sharedPreferences=getSharedPreferences(sp,Context.MODE_PRIVATE)

        binding.loginButton.setOnClickListener(){
            if (binding.emailEditText.text.toString().isEmpty() && binding.passwordEditText.text.toString().isEmpty()){
                Toast.makeText(this,"Harus semua berisi",Toast.LENGTH_SHORT).show()
            }
            else{
                var getEmail=sharedPreferences.getString(email,null)
                var getPass=sharedPreferences.getString(pass,null)

                if (binding.emailEditText.text.toString()==getEmail && binding.passwordEditText.text.toString()==getPass){
                    var intent=Intent(this,Dashboard::class.java)
                    intent.putExtra(email,getEmail)
                    intent.putExtra(pass,getPass)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Email dan pass salah",Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.registerLink.setOnClickListener(){
            var intent=Intent(this,Register::class.java)
            startActivity(intent)
        }
    }
}