package com.example.doaharian

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.doaharian.databinding.ActivityDetailBinding
import com.loopj.android.http.AsyncHttpClient

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val doa = intent.getStringExtra("doa")
        val ayat = intent.getStringExtra("ayat")
        val latin = intent.getStringExtra("latin")
        val artinya = intent.getStringExtra("artinya")

        binding.judul.text = doa
        binding.ayat.text = ayat
        binding.latin.text = latin
        binding.arti.text = artinya

    }
}