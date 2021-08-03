package com.schratzenstaller.wilcilene.projeto_integrador

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schratzenstaller.wilcilene.projeto_integrador.RecyclerAdapterInfoGenres.ViewHolder

class RecyclerAdapterInfoGenres: RecyclerView.Adapter<RecyclerAdapterInfoGenres.ViewHolder>() {

        private val genre = arrayOf("28"
        , "32"
        , "35")

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemGenre: TextView = itemView.findViewById(R.id.tvGenres)

        init {
            itemGenre.setOnClickListener {
                val position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, MovieDetail::class.java).apply { //carregar na RecyclerView
                    putExtra("NUMBER", position)
                    putExtra("GENRE", itemGenre.text)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_info_genres, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemGenre.text = genre[i]
    }

    override fun getItemCount(): Int {
        return genre.size
    }
}

