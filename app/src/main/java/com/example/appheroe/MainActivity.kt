package com.example.appheroe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appheroe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val superHeroName = binding.superheroNameEdit.text.toString()
            val alterEgo = binding.alterEgoEdit.text.toString()
            val bio = binding.bioEdit.text.toString()
            val power = binding.powerBar.rating
            val hero = Superheros(superHeroName, alterEgo, bio, power)
            openDetailActivity(hero)
        }
    }
    private fun openDetailActivity(superheros: Superheros) {
        val intent  = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.SUPER, superheros)
        startActivity(intent)
    }
}

