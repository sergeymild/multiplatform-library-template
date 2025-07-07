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
        publishLibraryVariants("release")
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
                implementation("org.jetbrains.compose.runtime:runtime:1.6.0")
                implementation("org.jetbrains.compose.foundation:foundation:1.6.0")
                implementation("org.jetbrains.compose.material3:material3:1.6.0")
                implementation("org.jetbrains.compose.ui:ui:1.6.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.compose.ui)
                implementation(libs.compose.ui.tooling.preview)
                implementation(libs.compose.foundation)
                implementation(libs.compose.material3)
            }
        }
    }
}

android {
    namespace = "org.jetbrains.kotlinx.multiplatform.library.template"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
