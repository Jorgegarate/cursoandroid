package com.example.appheroe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appheroe.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val SUPER ="super"
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
        println("hola ${superhero.toString()}")
        binding.heroName.text = superhero.name
        binding.alterEgo.text = superhero.alterEgo
        binding.bio.text = superhero.bio
        binding.power.rating = superhero.power

    }
}