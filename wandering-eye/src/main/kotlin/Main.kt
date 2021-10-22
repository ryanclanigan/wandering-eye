package com.wandering

import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.wandering.card.checkers.starcity.StarCityCardChecker


/*
 * This can be replaced with any method to load the bot token.  This specific method is provided only for convenience
 * and as a way to prevent accidental disclosure of bot tokens.
 *
 * Alternative methods might include reading from the environment, using a system property, or reading from the CLI.
 */
private val BOT_TOKEN = try {
    ClassLoader.getSystemResource("bot-token.txt").readText().trim()
} catch (error: Exception) {
    throw RuntimeException(
        "Failed to load bot token. Make sure to create a file named bot-token.txt in" +
                " src/main/resources and paste the bot token into that file.", error
    )
}

suspend
fun main() {
    val starCity = StarCityCardChecker()
    bot(BOT_TOKEN) {
        classicCommands(",,") {
            command("card") { message ->
                val cardName = message.content.substringAfter("card")
                val card = starCity.fetchCard(cardName)
                message.respond("${card.name} has ${card.count} copies at ${starCity.getName()}")
            }

            command("jim") { message ->
                message.respond("jom")
            }
        }
    }
}
