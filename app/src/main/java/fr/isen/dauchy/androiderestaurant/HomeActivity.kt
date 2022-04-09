package fr.isen.dauchy.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.dauchy.androiderestaurant.ble.BLEScanActivity
import fr.isen.dauchy.androiderestaurant.cart.ShoppingCartActivity


import fr.isen.dauchy.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEntrees.setOnClickListener {
           goToCategorie(getString((R.string.entr_es)))
        }
        binding.buttonPlats.setOnClickListener {
            goToCategorie(getString((R.string.plats)))
        }
        binding.buttonDesserts.setOnClickListener {
            goToCategorie(getString((R.string.desserts)))
        }



    }

    private  fun goToCategorie(categorie: String){
        val intent = Intent(this,CategorieActivity::class.java)
        intent.putExtra("categorie", categorie)
        startActivity(intent)
    }
    private val TAG = "HomeActivity"

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Page détruite");
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Quitte page acceuille ");
    }
    fun displayEntreesMsg(view: View) {
        toastMsg("Entrées")
    }

    private fun toastMsg(msg: String) {
        val toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        toast.show()

    }

    fun displayPlatsMsg(view: View) {
        toastMsg("Plats")
    }
    fun displayDessertsMsg(view: View) {
        toastMsg("Desserts")
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
        Toast.makeText(this@HomeActivity, "panier", Toast.LENGTH_SHORT).show()
        true

    }

    private fun bluetooth() {
        val intent = Intent(this, BLEScanActivity::class.java)
        startActivity(intent)
        Toast.makeText(this@HomeActivity, "Bluetooth", Toast.LENGTH_SHORT).show()
        true

    }
}