package com.wandering.card.checkers

interface CardChecker {
    fun fetchCard(cardName: String): Card
    fun getName(): String
}