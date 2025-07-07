pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        // Add the Google Maven repository with a specific URL for androidx.compose.compiler:compiler:1.6.0
        maven("https://androidx.dev/storage/compose-compiler/repository")
    }
}

rootProject.name = "multiplatform-library-template"
include(":library")
include(":example-android")
include(":example-ios")
