package com.example.masjid_annur.api

data class FaqItem (
    val question: String,
    val answer: String,
    var isExpanded: Boolean = false
)