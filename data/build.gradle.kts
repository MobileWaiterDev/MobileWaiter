import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK
    }

    buildTypes.forEach {
        val properties = Properties()
        properties.load(FileInputStream(file("./../conf.properties")))
        val urlBase = properties.getProperty("base_url", "")
        it.buildConfigField("String", "BASE_URL", urlBase)
        val appId = properties.getProperty("appid", "")
        it.buildConfigField("String", "APP_ID", appId)
        val appToken = properties.getProperty("token", "")
        it.buildConfigField("String", "API_TOKEN", appToken)
    }
}

dependencies {

    //Modules
    implementation(project(Modules.MODULE_DOMAIN))

    // Kotlin
    implementation(Kotlin.CORE)
    implementation(Kotlin.STDLIB)

    // Design
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.CONSTRAINT_LAYOUT)

    // Retrofit
    implementation(Retrofit2.RETROFIT)
    implementation(Retrofit2.CONVERTER_JSON)
    implementation(Retrofit2.COROUTINES_ADAPTER)
    implementation(Retrofit2.LOGGING_INTERCEPTOR)

    //Tests
    testImplementation(Tests.JUNIT)
    androidTestImplementation(Tests.TEST_EXT_JUNIT)
    androidTestImplementation(Tests.ESPRESSO)
}