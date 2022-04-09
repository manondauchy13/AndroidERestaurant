package fr.isen.dauchy.androiderestaurant.cart

import fr.isen.dauchy.androiderestaurant.model.Item
import java.io.Serializable

class ItemShoppingCart (var cartItem: Item, var quantity:Int) : Serializable

