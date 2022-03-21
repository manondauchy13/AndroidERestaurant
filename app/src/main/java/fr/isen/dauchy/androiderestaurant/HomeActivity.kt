package fr.isen.dauchy.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.dauchy.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonEntrees.setOnClickListener {
            val intent = Intent(this,EntreesActivity::class.java)
            startActivity(intent)

        }
        binding.buttonPlats.setOnClickListener {
            val intent = Intent(this,PlatsActivity::class.java)
            startActivity(intent)

        }
        binding.buttonDesserts.setOnClickListener {
            val intent = Intent(this,DessertsActivity::class.java)
            startActivity(intent)

        }
    }
    private val tag = "HomeActivity"

    override fun onDestroy() {
        super.onDestroy()
        Log.i(tag, "Page détruite");
    }

    override fun onStop() {
        super.onStop()
        Log.i(tag, "Quitte page acceuille ");
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

}