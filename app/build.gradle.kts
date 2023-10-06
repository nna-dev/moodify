import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless")
}

android {
    namespace = "com.nna.moodify"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.nna.moodify"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localProperties: Properties = gradleLocalProperties(rootDir)
        buildConfigField(
            "String",
            "SPOTIFY_CLIENT_ID",
            "\"${localProperties["SPOTIFY_CLIENT_ID"]}\""
        )
        buildConfigField(
            "String",
            "SPOTIFY_CLIENT_SECRET",
            "\"${localProperties["SPOTIFY_CLIENT_SECRET"]}\""
        )
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    lint {
        abortOnError = false
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.v4)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)

    // Logger
    implementation(libs.timber)

    // ktx
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.fragment.ktx)

    // navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    coreLibraryDesugaring(libs.android.desugar)

    // DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // SharedPrefs
    implementation(libs.androidx.security.crypto)

    // Api call
    implementation(libs.sandwich)
    implementation(libs.sandwich.serialization)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.http3.logging.interceptor)
    implementation(libs.moshi.moshi)
    kapt(libs.moshi.kotlin.codegen)

    // Glide
    implementation(libs.glide)

    // Epoxy
    implementation(libs.epoxy.epoxy)
    implementation(libs.epoxy.binding)
    kapt(libs.epoxy.processor)

    // ExoPlayer
    implementation(libs.exoplayer.core)
    implementation(libs.exoplayer.ui)
}

kapt {
    correctErrorTypes = true
}

detekt {
    source.setFrom("src/main/java")
    parallel = false
    config.setFrom("../config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    ignoredBuildTypes = listOf("release")
    basePath = projectDir.absolutePath
}
