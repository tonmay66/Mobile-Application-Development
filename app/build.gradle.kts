plugins {
<<<<<<< HEAD
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.newsarticlereaderapp"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.newsarticlereaderapp"
        minSdk = 24
        targetSdk = 36
=======
<<<<<<< HEAD
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.example.photogalleryapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.photogalleryapp"
        minSdk = 24
        targetSdk = 35
=======
    alias(libs.plugins.android.application)
<<<<<<< HEAD
=======
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
>>>>>>> 531ca80b51bfc395e069b3e39bd4d405347e14d5
    alias(libs.plugins.kotlin.android)
}

android {
<<<<<<< HEAD
    namespace = "com.example.in_appbrowserlearningportalapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.in_appbrowserlearningportalapp"
        minSdk = 24
        targetSdk = 36
=======
    namespace = "com.example.studentregistrationformapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.studentregistrationformapp"
        minSdk = 24
        targetSdk = 35
>>>>>>> 92d34802c73be524b9ed8c8bcae072d8130a2bab
>>>>>>> 17ad233603d79270c303dbe930f45e713681f1ef
>>>>>>> 531ca80b51bfc395e069b3e39bd4d405347e14d5
>>>>>>> 86b314acfd5e407b5ffbf56abd692d6c9914aff2
>>>>>>> 68fbabf14224cb53a4b70bac2b79aa882e5bf447
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        viewBinding = true
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 86b314acfd5e407b5ffbf56abd692d6c9914aff2
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
<<<<<<< HEAD
    }
    buildFeatures {
        viewBinding = true
=======
=======
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
>>>>>>> 531ca80b51bfc395e069b3e39bd4d405347e14d5
>>>>>>> 86b314acfd5e407b5ffbf56abd692d6c9914aff2
>>>>>>> 68fbabf14224cb53a4b70bac2b79aa882e5bf447
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
<<<<<<< HEAD
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
=======
<<<<<<< HEAD
    implementation(libs.material)
    implementation(libs.androidx.cardview)
=======
<<<<<<< HEAD
>>>>>>> 86b314acfd5e407b5ffbf56abd692d6c9914aff2
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
<<<<<<< HEAD
}
=======
}
=======
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
>>>>>>> 531ca80b51bfc395e069b3e39bd4d405347e14d5
>>>>>>> 86b314acfd5e407b5ffbf56abd692d6c9914aff2
>>>>>>> 68fbabf14224cb53a4b70bac2b79aa882e5bf447
