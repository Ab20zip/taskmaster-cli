plugins {
    application
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
    implementation("io.github.microutils:kotlin-logging:3.0.4")
    implementation("com.github.ajalt.clikt:clikt:3.5.4")
    implementation("org.fusesource.jansi:jansi:2.4.0")
}

application {
    mainClass.set("com.altiran.taskmaster.TaskMaster")
}
