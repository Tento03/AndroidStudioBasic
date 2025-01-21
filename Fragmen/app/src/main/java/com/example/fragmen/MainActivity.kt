package com.example.fragmen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fragmentSatu= FragmentSatu()
        val fragment=supportFragmentManager.findFragmentByTag(FragmentSatu::class.java.simpleName);
        if (fragment !is FragmentSatu){
            supportFragmentManager.beginTransaction()
                .add(R.id.container,fragmentSatu,FragmentSatu::class.java.simpleName)
                .commit();
        }

    }
}