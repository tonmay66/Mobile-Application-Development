plugins {
    alias(libs.plugins.android.application)
<<<<<<< HEAD
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.universityeventmanagementapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.universityeventmanagementapp"
        minSdk = 24
        targetSdk = 35
=======
<<<<<<< HEAD
    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    namespace = "com.example.e_commerceproductlistingapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.e_commerceproductlistingapp"
        minSdk = 24
        targetSdk = 36
=======
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.studentregistrationformapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.studentregistrationformapp"
        minSdk = 24
        targetSdk = 35
>>>>>>> 92d34802c73be524b9ed8c8bcae072d8130a2bab
>>>>>>> 17ad233603d79270c303dbe930f45e713681f1ef
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
<<<<<<< HEAD
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
=======
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
<<<<<<< HEAD
    buildFeatures {
        viewBinding = true
=======
    kotlinOptions {
        jvmTarget = "11"
>>>>>>> 92d34802c73be524b9ed8c8bcae072d8130a2bab
>>>>>>> 17ad233603d79270c303dbe930f45e713681f1ef
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
<<<<<<< HEAD
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
=======
<<<<<<< HEAD
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.activity.ktx)
    
=======
>>>>>>> 92d34802c73be524b9ed8c8bcae072d8130a2bab
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
>>>>>>> 17ad233603d79270c303dbe930f45e713681f1ef
