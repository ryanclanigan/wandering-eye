plugins {
    kotlin("jvm")
}

group = "com.wandering"
description = "Checks the cards"

dependencies {
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("com.github.kittinunf.fuel:fuel-gson:2.3.1")
}