package diegoz.zeldar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ViewAdapter(val items:List<Item>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista= LayoutInflater.from(parent.context).inflate(
            R.layout.zeldaitem,
            parent,
            false
        )
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int=items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=items[position].nombre
        holder.desc.text=items[position].desc
        try{
            Picasso.get().load(items[position].image).into(holder.image)
        }catch (ex: Exception){
            println("Error")
        }
    }
}