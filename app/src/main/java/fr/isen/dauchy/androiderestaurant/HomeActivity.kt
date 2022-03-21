package fr.isen.dauchy.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.Toast
import fr.isen.dauchy.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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