import java.util.Properties
import java.io.File

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.diffplug.spotless") version "6.21.0"
}

val keystorePropertiesFile = File(project.rootDir, "local.properties")
val keystoreProperties = Properties()

if (keystorePropertiesFile.exists()) {
    try {
        keystoreProperties.load(keystorePropertiesFile.inputStream())
    } catch (e: Exception) {
        throw GradleException("Failed to load keystore properties", e)
    }
} else {
    logger.warn("local.properties file not found. Skipping keystore setup.")
}

android {
    namespace = "com.ocode.well"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ocode.well"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val storeFilePath = keystoreProperties.getProperty("KEYSTORE_FILE")
            val storePassword = keystoreProperties.getProperty("KEYSTORE_PASSWORD")
            val keyAlias = keystoreProperties.getProperty("KEY_ALIAS")
            val keyPassword = keystoreProperties.getProperty("KEY_PASSWORD")

            if (
                storeFilePath != null && storePassword != null &&
                keyAlias != null && keyPassword != null
            ) {
                storeFile = file(storeFilePath)
                this.storePassword = storePassword
                this.keyAlias = keyAlias
                this.keyPassword = keyPassword
            } else {
                throw GradleException("Missing keystore properties in local.properties.")
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
            signingConfig = signingConfigs.getByName("release")
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
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Lifecycle + ViewModel + LiveData (MVVM)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // AndroidX Core + AppCompat + Material
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

    // Activity KTX
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material:material:1.5.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")

    implementation("androidx.navigation:navigation-fragment-ktx:2.8.8")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.8")

    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.3")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.3")
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint("0.50.0").userData(
            mapOf(
                "indent_size" to "4",
                "continuation_indent_size" to "4"
            )
        )
    }
    kotlinGradle {
        target("**/*.kts")
        ktlint("0.50.0")
    }
}
