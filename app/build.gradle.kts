plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
}

android {
    namespace = "com.west.cstack"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.west.cstack"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        externalNativeBuild {
            cmake {
                cppFlags += "-std=c++17"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

dependencies {
    // Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Jetpack Compose dependencies
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.compose.ui:ui:1.8.1")
    implementation("androidx.compose.material3:material3:1.3.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.8.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.8.1")

    // Navigation for Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.9.0")
    // Navigation Compose library for navigation
    implementation("androidx.navigation:navigation-compose:2.9.0")
    // Navigation Compose library for navigation
    implementation("androidx.navigation:navigation-compose:2.9.0")
    implementation("androidx.navigation:navigation-compose:2.9.0")
    implementation("androidx.compose.ui:ui:1.8.1") // Use a compatible version
    dependencies {
        implementation("androidx.compose.ui:ui:1.8.1")
        implementation("androidx.compose.material3:material3:1.3.2")
        implementation("androidx.compose.ui:ui-tooling-preview:1.8.1")
        implementation("androidx.activity:activity-compose:1.10.1")
        implementation("androidx.navigation:navigation-compose:2.9.0") // for Navigation
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.0") // for LiveData
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0") // for ViewModel integration with Compose
        debugImplementation("androidx.compose.ui:ui-tooling:1.8.1")

        // Jetpack Compose dependencies
        implementation("androidx.compose.ui:ui:1.8.1")
        implementation("androidx.compose.material3:material3:1.1.2")
        implementation("androidx.activity:activity-compose:1.10.1")
        implementation("androidx.navigation:navigation-compose:2.9.0") // for navigation
        debugImplementation("androidx.compose.ui:ui-tooling:1.8.1")

        // LiveData and ViewModel dependencies
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.0") // for LiveData
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0") // for ViewModel integration with Compose





        // Jetpack Compose dependencies
        implementation("androidx.compose.ui:ui:1.8.1")
        implementation("androidx.compose.material3:material3:1.3.2")
        implementation("androidx.activity:activity-compose:1.10.1")
        implementation("androidx.navigation:navigation-compose:2.9.0") // for navigation
        debugImplementation("androidx.compose.ui:ui-tooling:1.8.1")

        // Lifecycle dependencies (LiveData and ViewModel)
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.0") // for LiveData
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0") // for ViewModel integration with Compose

        // Optional testing dependencies
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
        implementation("androidx.compose.runtime:runtime-livedata:1.8.1")
        implementation("androidx.compose.material3:material3:1.3.2")



        // Optional testing dependencies
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)

    }







    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
