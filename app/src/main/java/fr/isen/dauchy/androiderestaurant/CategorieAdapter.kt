package fr.isen.dauchy.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.dauchy.androiderestaurant.model.Item

// gérer l'association d'une vue de la liste via la donnée

internal class CategorieAdapter(val data: ArrayList<Item>, val clickListener: (Item) -> Unit) :
    RecyclerView.Adapter<CategorieAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var deviceTextView: TextView = view.findViewById(R.id.deviceTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        holder.deviceTextView.text = item.name_fr



        holder.itemView.setOnClickListener{
            clickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
