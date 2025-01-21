package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var userAdapter: UserAdapter
    lateinit var userList:ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView=binding.rv
        recyclerView.layoutManager=LinearLayoutManager(this)

        userList= ArrayList()
        addList()
        userAdapter=UserAdapter(userList)
        recyclerView.adapter=userAdapter
    }

    private fun addList() {
        userList.add(User("tento", "121"))
        userList.add(User("luffy", "120"))
        userList.add(User("nami", "120"))
    }
}