package com.example.doaharian.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doaharian.R

class DoaAdapter(private val listDoa: ArrayList<String>) : RecyclerView.Adapter<DoaAdapter.ListViewHolder>() {
    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDoa: TextView = itemView.findViewById(R.id.tv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_doa, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.tvDoa.text = listDoa[position]
    }

    override fun getItemCount(): Int = listDoa.size
}