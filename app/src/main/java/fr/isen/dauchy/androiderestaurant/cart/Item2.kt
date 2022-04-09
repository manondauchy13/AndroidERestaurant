package fr.isen.dauchy.androiderestaurant.cart

import fr.isen.dauchy.androiderestaurant.model.Ingredient
import fr.isen.dauchy.androiderestaurant.model.Price
import java.io.Serializable


data class Item2(val id:String, val name_fr:String, val id_category:String, val categ_name_fr:String, val image:ArrayList<String>, val ingredient:ArrayList<Ingredient>, val price:ArrayList<Price>) :
    Serializable