plugins {
    id ("com.android.application")
    kotlin("android")
    id ("kotlin-kapt")
    id ("kotlin-android-extensions")
    id ("androidx.navigation.safeargs.kotlin")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.elnemr.movieFlix"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }


    configurations.all {
        resolutionStrategy.force("com.google.code.findbugs:jsr305:1.3.9")
    }

    compileOptions {
        sourceCompatibility = ConfigData.sourceCompatibility
        targetCompatibility = ConfigData.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = ConfigData.jvmTarget
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {
    implementation(project(":core"))

    // Splash
    implementation(Libs.splash)

    // Core
    implementation(Libs.core_ktx)
    implementation(Libs.app_compat)
    implementation(Libs.material)
    implementation(Libs.legacy_support)

    // ViewModel
    implementation(Libs.viewModel)

    // Coroutine
    implementation(Libs.coroutines_core)
    implementation(Libs.coroutines_android)

    // Coil
    implementation(Libs.coil)

    // Room
    implementation(Libs.room_runtime)
    implementation(Libs.room_ktx)
    kapt(Libs.room_compiler_ksp)

    // Hilt
    implementation(Libs.hilt_android)
    kapt(Libs.hilt_compiler_dagger_kapt)
    kapt(Libs.hilt_compiler_android_kapt)

    // Paging
    implementation(Libs.paging)

    // Navigation Component
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)

    // Shimmer
    implementation(Libs.shimmer_facebook)
    implementation(Libs.shimmer_recyclerView)

}