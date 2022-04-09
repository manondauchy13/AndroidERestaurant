package fr.isen.dauchy.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.dauchy.androiderestaurant.databinding.ActivityCategorieBinding
import fr.isen.dauchy.androiderestaurant.model.DataResult
import org.json.JSONObject

class CategorieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategorieBinding
    private lateinit var items: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategorieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        items = arrayListOf()
        val categorieName = intent.getStringExtra("categorie")
        binding.titreCategorie.text = categorieName

        //recycler view gérer une liste d'éléments

        afficheEntrees()

        getDataFromApi(intent.getStringExtra("categorie") ?: "")
        Log.d("LogApi", items.toString())
    }

    // ternaire affecter une valuer en fonction d'une condiation true or false par exemple ?: est un ternaire
    //?: si l'élément que je test est diff de nul alors je renvoie un élément sinon je renvoir une arrayList

        fun afficheEntrees(){
           // val entreesList = resources.getStringArray(R.array.entreesList).toList()
            val recyclerView: RecyclerView = binding.listeCategorie
            val layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = CategorieAdapter(arrayListOf()) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(ITEM_KEY, it)
                startActivity(intent)
            }
        }
        companion object{
            val ITEM_KEY = "item"
        }

        private fun getDataFromApi(category: String){
            val queue = Volley.newRequestQueue(this)
            val url = "http://test.api.catering.bluecodegames.com/menu"

            val json = JSONObject()
            json.put("id_shop", "1")
            json.toString()
            val request = JsonObjectRequest( Request.Method.POST,url,json,
                Response.Listener { response ->
                    val strResp = response.toString()
                    val data = Gson().fromJson(strResp, DataResult::class.java)
                    val items = data.data.firstOrNull{it.name_fr == category}?.items ?: arrayListOf()
                    binding.listeCategorie.adapter = CategorieAdapter(items)
                    {
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra(ITEM_KEY, it)
                        startActivity(intent)
                    }
                },  {
                    Log.e(TAG, "Log Volley error: $it" )

                })
            VolleySingleton.getInstance(this).addToRequestQueue(request)
        }
        private val TAG = "LogCategorieActivity"

        override fun onStop() {
            super.onStop()
            Log.d(TAG, "Sortie de la page des categories")
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.d(TAG, "Page des catégories détruite")
        }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bluetooth -> {
            Toast.makeText(this@CategorieActivity, "Bluetooth", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.panier -> {
            Toast.makeText(this@CategorieActivity, "Panier", Toast.LENGTH_SHORT).show()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    }