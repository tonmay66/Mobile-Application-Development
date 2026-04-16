plugins {
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
    kotlinOptions {
        jvmTarget = "11"
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
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
<<<<<<< HEAD
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
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
