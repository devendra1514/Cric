package com.example.cric.id

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Id {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getIds(): MutableList<Int> {

            val todayId = mutableListOf<Int>()

            val currentTime = LocalDateTime.now()

            val formatted = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            val data = FixtureByDateApi.fixtureInstance.getMatchId(formatted)

            data.enqueue(object : Callback<FixtureByDate> {
                override fun onResponse(
                    call: Call<FixtureByDate>,
                    response: Response<FixtureByDate>
                ) {
                    val fixtureByDate = response.body()
                    if (fixtureByDate != null) {
                        for (f in fixtureByDate.results) {
                            todayId.add(f.id)
                        }
                    }
                }

                override fun onFailure(call: Call<FixtureByDate>, t: Throwable) {
                    Log.d("Aao", "fixture by date don't give ids")

                }
            })
            return todayId
        }

    }

}