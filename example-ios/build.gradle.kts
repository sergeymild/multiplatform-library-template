plugins {
    id("org.jetbrains.kotlin.native.cocoapods")
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Example iOS app for Kotlin Multiplatform Library"
        homepage = "https://github.com/kotlin/multiplatform-library-template"
        version = "1.0.0"
        podfile = project.file("Podfile")

        framework {
            baseName = "example_ios"
            isStatic = true
        }

        // Remove the pod configuration as it's causing issues
        // We'll rely on the Podfile to reference the library
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":library"))
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

tasks.register("buildXcode") {
    dependsOn("podInstall")
    doLast {
        println("iOS example app is ready to be opened in Xcode")
        println("Open example-ios/example-ios.xcodeproj in Xcode")
    }
}
