package com.schratzenstaller.wilcilene.projeto_integrador

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
/*    @GET("/genre/movie/list")
    fun getGenreList(
          @Query("api_key") api_key: String = "ae93539e5e6476feaacec7675254f488"
        , @Query("language") languageString: String = "pt-BR"
    //https://api.themoviedb.org/3/genre/movie/list?api_key=ae93539e5e6476feaacec7675254f488&language=pt-BR
    ): Call<GenreListResult>*/
// buscar por genero
    //https://api.themoviedb.org/3/discover/movie?api_key=ae93539e5e6476feaacec7675254f488&language=en-US&sort_by=popularity.asc&with_genres=28
// todos os filmes
//    https://api.themoviedb.org/3/discover/movie
//    ?api_key=ae93539e5e6476feaacec7675254f488&language=en-US&sort_by=popularity.asc
    /*    fun searchPosterMovie(
        base_url_poster: String = "https://api.themoviedb.org/3/"
        , file_size: String = "/original"
        , api_key: String = "ae93539e5e6476feaacec7675254f488"
        , languageString: String = "&language=en"
    )*/
    @GET("https://api.themoviedb.org/3/discover/movie")
    fun getAllMovies(
      @Query("api_key") api_key: String = "ae93539e5e6476feaacec7675254f488"
    , @Query("language") language: String = "pt-BR"
    , @Query("sort_by") sort_by: String = "popularity.asc"
    ): Call<PageMovies>
}