package com.burakoyke.artbookapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burakoyke.artbookapp.databinding.CardDesignBinding

class ArtAdapter (val artList : ArrayList<Art>) : RecyclerView.Adapter<ArtAdapter.ArtHolder>() {
    inner class ArtHolder(val binding : CardDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val binding = CardDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)
    }

    override fun getItemCount(): Int {
        return artList.size
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.binding.textView.text = artList.get(position).artname
        holder.itemView.setOnClickListener() {
            val intent = Intent(holder.itemView.context,DetailsActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("id",artList[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }
}