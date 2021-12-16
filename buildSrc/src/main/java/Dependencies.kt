import org.gradle.api.JavaVersion

object Config {
    const val APPLICATION_ID = "com.mwaiterdev.waiter"
    const val COMPILE_SDK = 31
    const val MIN_SDK_VERSION = 28
    const val TARGET_SDK = 31
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val JVM_TARGET = "1.8"
    val java_version = JavaVersion.VERSION_1_8
}

//Наши модули
object Modules {
    const val MODULE_CORE = ":core"
    const val MODULE_MODELS = ":models"
    const val MODULE_REPOSITORY = ":repository"
    const val MODULE_UTILS = ":utils"
    const val MODULE_UI = ":ui"
}

//Версии библиотек
object LibVersion {
    const val KOTLIN_STDLIB_VERSION = "kotlin-stdlib-jdk8:1.5.31"
    const val GRADLE_VERSION = "7.0.2"
    const val GRADLE_PLUGIN_VERSION = "kotlin-gradle-plugin:1.5.31"
    const val CORE_KTX_VERSION = "1.7.0"
    const val APPCOMPAT_VERSION = "1.3.1"
    const val ANDROID_MATERIAL_VERSION = "1.4.0"
    const val CONSTRAINT_LAYOUT_VERSION = "2.1.2"

    // Tests
    const val JUNIT_VERSION = "4.13.2"
    const val TEST_EXT_JUNIT_VERSION = "1.1.3"
    const val TEST_ESPRESSO_VERSION = "3.4.0"

}

object Design {
    const val APPCOMPAT = "androidx.appcompat:appcompat:${LibVersion.APPCOMPAT_VERSION}"
    const val MATERIAL = "com.google.android.material:material:${LibVersion.ANDROID_MATERIAL_VERSION}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${LibVersion.CONSTRAINT_LAYOUT_VERSION}"
}

object Kotlin {
    const val STDLIB = "org.jetbrains.kotlin:${LibVersion.KOTLIN_STDLIB_VERSION}"
    const val CORE = "androidx.core:core-ktx:${LibVersion.CORE_KTX_VERSION}"
}

object Tests {
    const val JUNIT = "junit:junit:${LibVersion.JUNIT_VERSION}"
    const val TEST_EXT_JUNIT = "androidx.test.ext:junit:${LibVersion.TEST_EXT_JUNIT_VERSION}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${LibVersion.TEST_ESPRESSO_VERSION}"
}