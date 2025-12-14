plugins {
    kotlin("jvm") version "1.7.21"
    id("application")
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}

application {
    mainClass.set("MainKt")
}
