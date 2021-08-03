package com.schratzenstaller.wilcilene.projeto_integrador
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_all_movies.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllMoviesFragment : Fragment() {
    private lateinit var retrofit: Retrofit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_movies, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {

        super.onViewCreated(itemView, savedInstanceState)

        rvGenres.apply {
            //layoutManager = LinearLayoutManager(activity)
            LinearLayoutManager.HORIZONTAL
            adapter = RecyclerAdapterInfoGenres()
        }

        rvMovies.apply {
            //layoutManager = LinearLayoutManager(activity)
            LinearLayoutManager.HORIZONTAL
            adapter = RecyclerAdapterInfoMovies()
        }

    }
}

