package fr.isen.dauchy.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import fr.isen.dauchy.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.dauchy.androiderestaurant.model.Item

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    lateinit var quantity: TextView
    lateinit var boutonPlus: Button
    lateinit var boutonMoins: Button
    private lateinit var item: Item
    var countInt: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getSerializableExtra(CategorieActivity.ITEM_KEY) as Item

        binding.detailTitle.text= item.name_fr

        binding.listeIngredients.text = item.ingredients.joinToString { it.name_fr }

        val carouselAdapter = CarouselAdapter(this,item.images)
        binding.detailSlider.adapter = carouselAdapter

        boutonPlus = findViewById(R.id.boutonPlus)
        boutonMoins = findViewById(R.id.boutonMoins)
        quantity = findViewById(R.id.quantity)
        quantity.text = countInt.toString()
        doTotal(item, countInt)

        boutonPlus.setOnClickListener {
            if (countInt >= 0){
                countInt++
                quantity.text = countInt.toString()
                doTotal(item, countInt)
            }
        }

        boutonMoins.setOnClickListener {
            if (countInt > 0){
                countInt--
                quantity.text = countInt.toString()
                doTotal(item, countInt)
            }
        }

    }
    private fun doTotal(item: Item, selected: Float) {
        val ttlPrix: String = item.prices[0].price
        val total: Float = ttlPrix.toFloat() * selected
        val totalString: String = "Total : " + total.toString() + "€"
        binding.total.text = totalString
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bluetooth -> {
            Toast.makeText(this@DetailActivity, "Bluetooth", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.panier -> {
            Toast.makeText(this@DetailActivity, "Panier", Toast.LENGTH_SHORT).show()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }


    }
    }