package com.example.doaharian.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doaharian.DetailActivity
import com.example.doaharian.R
import com.example.doaharian.dataclass.DataDoa

class DoaAdapter(private val listDoa: ArrayList<DataDoa>) : RecyclerView.Adapter<DoaAdapter.ListViewHolder>() {

    private lateinit var onItemOnClickCallback: OnItemClickCallback

    interface OnItemClickCallback{
        fun onItemClicked(data: String)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemOnClickCallback = onItemClickCallback
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDoa: TextView = itemView.findViewById(R.id.tv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_doa, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val doa = listDoa[position]
        holder.tvDoa.text = doa.doa
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("doa", doa.doa)
            intent.putExtra("ayat", doa.ayat)
            intent.putExtra("latin", doa.latin)
            intent.putExtra("artinya", doa.arti)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listDoa.size
}