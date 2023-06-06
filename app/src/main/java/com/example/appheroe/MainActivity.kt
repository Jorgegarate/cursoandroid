package com.example.appheroe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.appheroe.databinding.ActivityMainBinding
import android.app.Activity
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap

class MainActivity : AppCompatActivity() {
    private lateinit var superheroImage: ImageView
    private var heroBitmap: Bitmap? = null
    val getContent = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        bitmap ->
        heroBitmap = bitmap
        superheroImage.setImageBitmap(heroBitmap!!)
    }
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
        superheroImage= binding.superheroImage
        superheroImage.setOnClickListener {
            openCamara()
        }
    }

    private fun openCamara() {
        getContent.launch(null)
    }

    private fun openDetailActivity(superheros: Superheros) {
        val intent  = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.SUPER, superheros)
        intent.putExtra(DetailActivity.BITMAP, superheroImage.drawable.toBitmap())
        startActivity(intent)
    }

}

