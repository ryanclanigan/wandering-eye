plugins {
    kotlin("jvm")
    application
}

group = "com.wandering"
description = "full-thing"

dependencies {
    implementation(project(":card-checkers"))
    implementation("com.jessecorbett:diskord-bot:2.1.1")
}

application {
    mainClass.set("com.wandering.MainKt")
}
