package com.schratzenstaller.wilcilene.projeto_integrador

import com.google.gson.annotations.SerializedName

data class PageMovies (
    @SerializedName("page")
    val page: String?,

    @SerializedName("results")
    val results: List<MovieListResult>?
)

data class MovieListResult(
        //adicionar todos os atributos do filme aqui
        @SerializedName("id")
        val id: String?,

        @SerializedName("title")
        val title: String?,

        @SerializedName("genre_ids")
        val genre_ids: List<Int>?,

        @SerializedName("poster_path")
        val poster_path: String?
    )


