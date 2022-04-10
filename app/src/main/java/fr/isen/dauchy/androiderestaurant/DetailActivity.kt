package fr.isen.dauchy.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import fr.isen.dauchy.androiderestaurant.ble.BLEScanActivity
import fr.isen.dauchy.androiderestaurant.cart.Cart
import fr.isen.dauchy.androiderestaurant.cart.ShoppingCartActivity
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
        /*binding.total.setOnClickListener {
            item?.let { item ->
                this.cacheDir.absolutePath
                val cart = Cart.getCart(this)
                cart.addItem(item, countInt.toInt())
                cart.save(this)
                Snackbar.make(binding.total, R.string.itemAdded, Snackbar.LENGTH_LONG).show()
                invalidateOptionsMenu()
            }
        }*/
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.bluetooth -> {
                bluetooth()
                true
            }
            R.id.panier -> {
                panier()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun panier() {
        val intent = Intent(this, ShoppingCartActivity::class.java)
        startActivity(intent)
        Toast.makeText(this@DetailActivity, "panier", Toast.LENGTH_SHORT).show()
        true

    }

    private fun bluetooth() {
        val intent = Intent(this, BLEScanActivity::class.java)
        startActivity(intent)
        Toast.makeText(this@DetailActivity, "Bluetooth", Toast.LENGTH_SHORT).show()
        true

    }

    }