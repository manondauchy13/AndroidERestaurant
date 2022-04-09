package fr.isen.dauchy.androiderestaurant.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import fr.isen.dauchy.androiderestaurant.databinding.ActivityShoppingCartBinding

class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var monRecycler: RecyclerView
    private lateinit var binding: ActivityShoppingCartBinding
    private lateinit var panierList: ShoppingCartList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}