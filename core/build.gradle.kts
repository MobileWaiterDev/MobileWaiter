plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Config.java_version
        targetCompatibility = Config.java_version
    }

    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }

    buildFeatures {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {

    // Kotlin
    implementation(Kotlin.CORE)

    // Design
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.CONSTRAINT_LAYOUT)

    //Tests
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}