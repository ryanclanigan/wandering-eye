package com.wandering.card.checkers

data class Card(
    val name: String,
    val count: Int,
    val seller: String,
    val properties: List<Any>?
) {
    constructor(name: String, count: Int, seller: String) : this(name, count, seller, null)
}