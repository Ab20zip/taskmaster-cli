plugins {
    application
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("com.github.ajalt.clikt:clikt:4.4.0")
    implementation("org.fusesource.jansi:jansi:2.4.1")
}

application {
    mainClass.set("com.altiran.taskmaster.TaskMaster")
}
