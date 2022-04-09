package fr.isen.dauchy.androiderestaurant


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.dauchy.androiderestaurant.model.Item

// gérer l'association d'une vue de la liste via la donnée

internal class CategorieAdapter(val itemsList: ArrayList<Item>, val clickListener: (Item) -> Unit
) :
    RecyclerView.Adapter<CategorieAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemTextView)
        var itemLogo: ImageView = view.findViewById(R.id.itemLogo)
        var priceTextView: TextView = view.findViewById(R.id.priceTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item.name_fr

        val prix: String = item.prices[0].price + "€"
        holder.priceTextView.text = prix



        Picasso.get().load(item.images[0].ifEmpty { null })
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.itemLogo)

        if (item.name_fr.length > 25) {
            holder.itemTextView.text = item.name_fr.subSequence(0,25)
        }
        else {
            holder.itemTextView.text = item.name_fr
        }
        Log.d("Adapter : ", item.name_fr)

        holder.itemView.setOnClickListener {
         clickListener(item)
        }
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }

}
