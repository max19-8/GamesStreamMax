package com.example.gamesstreammax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesstreammax.databinding.ActivityMainBinding
import com.example.gamesstreammax.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, MainFragment())
                .commitAllowingStateLoss()
        }
    }
}