package fr.isen.dauchy.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.dauchy.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.dauchy.androiderestaurant.model.Item

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getSerializableExtra(CategorieActivity.ITEM_KEY) as Item

        binding.detailTitle.text= item.name_fr

        binding.listeIngredients.text = item.ingredients.joinToString { it.name_fr }

        val carouselAdapter = CarouselAdapter(this,item.images)
        binding.detailSlider.adapter = carouselAdapter


    }
}