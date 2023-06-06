package com.example.appheroe

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appheroe.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val SUPER ="super"
        const val BITMAP="foto"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val superhero = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(SUPER, Superheros::class.java)!!
        } else {
            intent.getParcelableExtra(SUPER)!!

        }
        val bitmapDirectory = intent.getStringExtra(BITMAP)

        val bitmap = BitmapFactory.decodeFile(bitmapDirectory)

        binding.superhero=superhero
        binding.imageBitmap.setImageBitmap(bitmap)

    }
}