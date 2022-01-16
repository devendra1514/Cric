package com.example.cric

import com.example.cric.scorecard.OutputScoreCard
import com.example.cric.scorecard.Results
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://cricket-live-data.p.rapidapi.com/"

interface CricService {
    @Headers("x-rapidapi-host:cricket-live-data.p.rapidapi.com",
        "x-rapidapi-key:106d4991f4msh7d6be57d1604a85p1b9496jsn4390e32777f8")
    @GET("match/2432999")
    fun getScoreCard(): Call<OutputScoreCard>
}

object CricApi{
    val scoreCardInstance: CricService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        scoreCardInstance = retrofit.create(CricService::class.java)
    }
}