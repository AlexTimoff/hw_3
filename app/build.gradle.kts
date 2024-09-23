plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.alextimofeev_hw3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.alextimofeev_hw3"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true;
    }
}

dependencies {
    implementation("androidx.fragment:fragment:1.8.1")
    implementation("androidx.fragment:fragment-ktx:1.8.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.2")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")

    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.github.bumptech.glide:glide:5.0.0-rc01")
    annotationProcessor("com.github.bumptech.glide:compiler:5.0.0-rc01")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}