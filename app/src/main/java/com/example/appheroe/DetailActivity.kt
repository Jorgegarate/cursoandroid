package com.example.appheroe

import android.graphics.Bitmap
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
        val bitmap = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(BITMAP, Bitmap::class.java)!!
        } else {
            intent.getParcelableExtra(BITMAP)!!

        }
        binding.superhero=superhero
        binding.imageBitmap.setImageBitmap(bitmap)

    }
}