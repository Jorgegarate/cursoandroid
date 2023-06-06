package com.example.appheroe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appheroe.databinding.ActivityMainBinding
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var superheroImage: ImageView
    private var heroBitmap: Bitmap? = null
    private var picturePath = ""

    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        success ->
        if (success && picturePath.isNotEmpty()) {
            heroBitmap = BitmapFactory.decodeFile(picturePath)
            if (heroBitmap != null) {
                superheroImage.setImageBitmap(heroBitmap!!)
            }
        }
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
        val file = creatImageFile()
        val uri = if  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                this, "$packageName.provider",
                file as File
            )
        } else {
            Uri.fromFile(file as File?)
        }
        getContent.launch(uri)
    }

    private fun creatImageFile(): Any {
        val fileName = "superhero_image"
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(fileName, ".jpg", fileDirectory)
        picturePath = file.absolutePath
        return file
    }

    private fun openDetailActivity(superheros: Superheros) {
        val intent  = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.SUPER, superheros)
        intent.putExtra(DetailActivity.BITMAP, picturePath)
        startActivity(intent)
    }

}

