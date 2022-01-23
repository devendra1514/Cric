package com.example.cric.scorecard_home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.cric.ScoreCardApi
import com.example.cric.id.Id
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetScoreCard {
    companion object {

        private val scoreCardList = mutableListOf<ScoreCard>()

        @RequiresApi(Build.VERSION_CODES.O)
        fun scoreCard(): MutableList<ScoreCard> {

            val idList = Id.getIds()

            for (id in idList) {
                val data = ScoreCardApi.scoreCardInstance.getScoreCard(id)
                data.enqueue(object : Callback<ScoreCard> {
                    override fun onResponse(call: Call<ScoreCard>, response: Response<ScoreCard>) {
                        if (response.body() != null) {
                            scoreCardList.add(response.body()!!)
                        }
                    }
                    override fun onFailure(call: Call<ScoreCard>, t: Throwable) {
                        Log.d("Aao", t.message.toString())
                    }

                })
            }

            return scoreCardList
        }
    }

}