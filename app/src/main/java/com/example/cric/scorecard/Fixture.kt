package com.example.cric.scorecard

class Fixture(val id: Int,
              val series_id: Int,
              val match_title: String,
              val start_date: String,
              val end_date: String,
              val dates: List<Dates>,
              val home: Home,
              val away: Away,
              val venue: String
) {
}
