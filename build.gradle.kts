plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
}

group = "com.abogomazov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

object Libs {
    private val kotest_version = "5.5.4"
    private val serialization_version = "1.5.1"
    private val korim_version = "4.0.9"

    val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization_version"
    val kotest = "io.kotest:kotest-runner-junit5:$kotest_version"
    val kotest_property = "io.kotest:kotest-property:$kotest_version"
    val korim = "com.soywiz.korlibs.korim:korim:$korim_version"
}

dependencies {
    implementation(Libs.serialization)
    implementation(Libs.korim)

    testImplementation(Libs.kotest)
    testImplementation(Libs.kotest_property)
}

tasks.test {
    useJUnitPlatform()
}
