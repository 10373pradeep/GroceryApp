package com.pkscoding.gapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRVAdapter(
    var list: List<GItems>,
    val grocerItemClickInterface: MainActivity
                 ) : RecyclerView.Adapter<GRVAdapter.GrocerViewHolder>() {

    
    
    
    
    
    
    inner class GrocerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTv = itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTv = itemView.findViewById<TextView>(R.id.idTVQuantity)
        val rateTV = itemView.findViewById<TextView>(R.id.idTVRate)
        val amountTv = itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val deleteIv = itemView.findViewById<ImageView>(R.id.idTVDelete)

    }
    
    
    
    interface GroceryItemClickInterface{
        fun onItemClick(gItems: GItems)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrocerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.g_rv_item,parent,false )
        return GrocerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: GrocerViewHolder, position: Int) {
        holder.nameTv.text = list.get(position).itemName
        holder.quantityTv.text = list.get(position).itemQuantity.toString()
        holder.rateTV.text = "Rs "+ list[position].itemPrice.toString()
        val itemTotal :Int = list[position].itemPrice * list.get(position).itemQuantity
        holder.amountTv.text = "Rs "+itemTotal.toString()
        holder.deleteIv.setOnClickListener {
            grocerItemClickInterface.onItemClick(list.get(position))
        }

    }


}