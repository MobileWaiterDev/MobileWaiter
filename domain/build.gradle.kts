plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = Config.java_version
    targetCompatibility = Config.java_version
}

dependencies {

    // Kotlin
    implementation(Kotlin.STDLIB)

    // Retrofit
    implementation(Retrofit2.RETROFIT)
    implementation(Retrofit2.CONVERTER_JSON)
    implementation(Retrofit2.COROUTINES_ADAPTER)
    implementation(Retrofit2.LOGGING_INTERCEPTOR)

    // Coroutines
    implementation(Coroutines.ANDROID)
    implementation(Coroutines.CORE)

    //Tests
    testImplementation(Tests.JUNIT)
}