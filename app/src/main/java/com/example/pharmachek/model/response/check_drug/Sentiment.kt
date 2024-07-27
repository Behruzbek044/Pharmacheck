package com.example.pharmachek.model.response.check_drug

data class Sentiment(
    val negative: Int,
    val neutral: Int,
    val positive: Int
)