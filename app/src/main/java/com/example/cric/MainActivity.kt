package com.example.cric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cric.scorecard.OutputScoreCard
import com.example.cric.scorecard.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            getScoreCard()
        }

    }
    private fun getScoreCard(){
        val data = CricApi.scoreCardInstance.getScoreCard()
        data.enqueue(object : Callback<OutputScoreCard> {
            override fun onResponse(
                call: Call<OutputScoreCard>,
                response: Response<OutputScoreCard>
            ) {
                val outputScoreCard = response.body()
                if (outputScoreCard != null) {
                    Log.d("Aao", outputScoreCard.results.fixture.match_title)
                }
            }

            override fun onFailure(call: Call<OutputScoreCard>, t: Throwable) {
                Log.d("Aao", t.message.toString())
            }
        })
    }
}