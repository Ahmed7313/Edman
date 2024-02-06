@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.daman.edman"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.daman.edman"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
//    androidExtensions {
//        experimental = true
//    }


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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    namespace = "com.daman.edman"
    applicationVariants.all {
        addJavaSourceFoldersToModel(
            File(buildDir, "generated/ksp/$name/kotlin")
        )
    }

}

dependencies {

    // Core Libraries
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    // Jetpack Compose Libraries
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.compose.navigation)
    //implementation(libs.compose.liveData)

    //icon
    implementation(libs.androidx.compose.material.material.icons.extended)

    // Hilt & Dagger - Dependency Injection
    implementation(libs.dagger.core)
    implementation(libs.dagger.lint.aar)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.navigation.compose)

    // Retrofit, OkHttp, and Moshi
    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.okHttp)
    implementation(libs.okHttpLoggingInterceptor)
    implementation(libs.scalarsConverter)
    implementation(libs.moshiKotlin)
    implementation(libs.moshiConverter)

    // Room - Local Database
//    implementation(libs.room.runtime)
//    ksp(libs.room.compiler)
//    implementation(libs.room.ktx)

    // Testing Libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Jetpack Compose Testing Libraries
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)

    // Debugging Libraries
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // Coil for image loading
    implementation(libs.coil)
    implementation(libs.coil.compose)

    //Lottie
    implementation(libs.lottie.compose)

    //Compose-Destinations
    implementation(libs.io.github.raamcosta.compose.destinations.animations.core)
    ksp(libs.io.github.raamcosta.compose.destinations.ksp)

    //Glide
    implementation(libs.glide)
    ksp(libs.glide.compiler)
    implementation(libs.glide.compose)

    //Timber for logging
    implementation(libs.timber)

    //Kotlin Coroutines and Flow for Compose
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    //Accompanists
    implementation(libs.accompanist.drawablepainter)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.placeholder)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.systemuicontroller)
//    implementation(libs.toasty)

    //ExpoPlayer
    implementation(libs.androidx.media3.exoplayer)

    //CameraX and image Crope
    implementation(libs.androidxCameraCamera2)
    implementation(libs.androidxCameraView)
    implementation(libs.androidxCameraLifecycle)
    implementation(libs.vanniktechImageCropper)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    implementation("com.github.TheHasnatBD:SweetToast:1.0.2")

    implementation("androidx.compose.material:material:1.1.0-beta01")

    //constrains Layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //paging 3
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)

    implementation(libs.androidx.biometric)
}