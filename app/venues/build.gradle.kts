import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0-Beta4"
    id("org.jlleitschuh.gradle.ktlint")
    id("com.google.dagger.hilt.android")
}

var fsqPlacesApiKey = "00000000000000000000000000000000"
try {
    val apikeyPropertiesFile = rootProject.file("secrets.properties")
    val apikeyProperties = Properties()
    apikeyProperties.load(FileInputStream(apikeyPropertiesFile))
    fsqPlacesApiKey = apikeyProperties["FSQ_PLACES_API_KEY"].toString()
} catch (e: FileNotFoundException) {
    logger.warn("No secrets.properties file found. Using default value: $fsqPlacesApiKey.")
} catch (e: Exception) {
    throw e
}

android {
    namespace = "com.yaabelozerov.venues"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "FSQ_PLACES_API_KEY", "\"" + fsqPlacesApiKey + "\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.hilt.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.play.services.location)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(project(":app:common"))
    implementation(project(":app:location"))
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.okhttp)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    ksp(libs.dagger.android.processor)
    implementation(libs.glide)
    implementation(libs.compose)
    ksp(libs.compiler)
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi)
    ksp(libs.moshi.kotlin.codegen)
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
}
