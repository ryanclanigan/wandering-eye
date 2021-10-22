package com.wandering.card.checkers.starcity


import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.wandering.card.checkers.Card
import com.wandering.card.checkers.CardChecker
import java.net.URLEncoder

class StarCityCardChecker : CardChecker {
    companion object {
        val QUANTITY_REGEX = Regex("QTY: (\\d+)")
    }

    override fun fetchCard(cardName: String): Card {
        val html = StarCityApi().getCardByExactText(cardName).html
        val quantity = QUANTITY_REGEX.findAll(html)
            .map { it.value.substring(5).toInt() }
            .sum()

        return Card(cardName, quantity, getName())
    }

    override fun getName(): String = "Star City Games"

}

internal class StarCityApi {
    companion object {
        val gson = Gson()

        private fun getSearchUrl(cardName: String): String {
            return "https://starcitygames.hawksearch.com/sites/starcitygames/?search_query=${
                URLEncoder.encode(
                    cardName,
                    "utf-8"
                )
            }&ajax=1&json=1"
        }
    }

    fun getCardByExactText(cardName: String): StarCityResponse {
        val (_, _, result) = getSearchUrl(cardName).httpGet().response()
        return when (result) {
            is Result.Success -> gson.fromJson(
                String(result.value).substring(1, result.value.size - 1),
                StarCityResponse::class.java
            )
            is Result.Failure -> throw result.error

        }
    }

    internal data class StarCityResponse(var html: String)
}