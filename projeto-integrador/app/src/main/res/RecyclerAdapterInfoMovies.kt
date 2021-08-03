package com.schratzenstaller.wilcilene.projeto_integrador

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.schratzenstaller.wilcilene.projeto_integrador.RecyclerAdapterInfoMovies.ViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecyclerAdapterInfoMovies: RecyclerView.Adapter<ViewHolder>() {
    val movies = listOf<MovieListResult>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TmdbService::class.java)

        val callback = service.getAllMovies()

        Log.i("ViewHolder", "TO AQUI")

        callback.enqueue(object : Callback<PageMovies> {
            override fun onResponse(call: Call<PageMovies>, response: Response<PageMovies>) {
                Log.i("ViewHolder", response.body().toString())
                response.body()?.results?.forEach {
                    movies.plus(it)
                    Log.i("ViewHolder", it.toString())
                }

                // Avisar que os items mudaram?
            }

            override fun onFailure(call: Call<PageMovies>, t: Throwable) {
                // ALTERAR
            }
        })

        notifyItemChanged(this.itemCount)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemPoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
        val itemTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        val itemPercent: TextView = itemView.findViewById(R.id.tvMoviePercent)

        init {
            itemPoster.setOnClickListener {
                val position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, MovieDetail::class.java).apply {
                    putExtra("NUMBER", position)
                    putExtra("IPOSTER", itemPoster.imageAlpha)
                    putExtra("TITLE", itemTitle.text)
                    putExtra("PERCENT", itemPercent.text)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_info_movies, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = movies[i].title
        viewHolder.itemPercent.text = "30%"
        Glide.with(viewHolder.itemPoster.context)
            .load("https://image.tmdb.org/t/p/original"+movies[i].poster_path)
            .into(viewHolder.itemPoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

