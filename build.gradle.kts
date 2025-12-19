plugins {
    kotlin("jvm") version "2.2.20"
    id("application")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }
}

application {
    mainClass.set("MainKt")
}
