package com.tsu.roman_encoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.tsu.roman_encoder.databinding.ActivityMainBinding
import com.tsu.roman_encoder.room.Dao
import com.tsu.roman_encoder.room.Database
import kotlinx.coroutines.CoroutineScope

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val input = binding.editTextNumber.text.toString()
            viewModel.getResult(input)
        }

        viewModel.result.observe(this) {
            binding.textView.text = it
        }
    }
}