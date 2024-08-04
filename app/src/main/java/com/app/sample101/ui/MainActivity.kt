package com.app.sample101.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.sample101.R
import com.app.sample101.databinding.ActivityMainBinding
import com.app.sample101.databinding.ItemUserBinding
import com.app.sample101.ui.adapters.UserListAdapter
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<UserViewModel>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = UserListAdapter()
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        lifecycleScope.launch {
            viewModel.getUserData()
            viewModel.userList.collectLatest {
                Log.d("TAG", "onCreate: ${it.size}")
                adapter.submitList(it)
            }
        }

    }
}