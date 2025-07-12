plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("org.jetbrains.kotlin.native.cocoapods")
    alias(libs.plugins.jetbrainsComposeCompiler)
    alias(libs.plugins.compose.compiler)
}

group = "io.github.kotlin"
version = "1.0.0"

kotlin {
    androidTarget {
        publishLibraryVariants("release", "debug")
        compilations.all {

        }
    }
    // Explicitly define iOS targets
    // iOS_ARM32 is no longer supported in Kotlin 2.2.0
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Kotlin Multiplatform Library"
        homepage = "https://github.com/kotlin/multiplatform-library-template"
        version = "1.0.0"
        ios.deploymentTarget = "13.0"
        framework {
            baseName = "library"
            isStatic = false
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation("org.jetbrains.compose.runtime:runtime:1.8.2")
                implementation("org.jetbrains.compose.foundation:foundation:1.8.2")
                implementation("org.jetbrains.compose.material3:material3:1.8.2")
                implementation("org.jetbrains.compose.ui:ui:1.8.2")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.compose.ui:ui:1.8.3")
                implementation("androidx.compose.ui:ui-tooling-preview:1.8.3")
                implementation("androidx.compose.foundation:foundation:1.8.3")
                implementation("androidx.compose.material3:material3:1.3.2")
            }
        }
    }
}

android {
    namespace = "org.jetbrains.kotlinx.multiplatform.library.template"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
