package com.csgames.mixparadise.adapter
import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.annotation.NonNull
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.csgames.mixparadise.R
import com.csgames.mixparadise.model.Ingredient
import com.csgames.mixparadise.model.Juice
import kotlinx.android.synthetic.main.view_ingredient_item.view.*


class JuiceAdapter(var items:List<Juice>, val context: Context, val listener: OnItemClickListener) : RecyclerView.Adapter<JuiceAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.view_ingredient_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items.get(position);

        holder.bind(item);

        holder.itemView.setOnClickListener { _ ->
            listener.onItemClickListener(item)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Juice) {
            itemView.title.setText(item.label)
            Glide.with(itemView.context).load(item.imageUrl).into(itemView.image_view);
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(item: Juice)
    }
}