plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dev.reformator.stacktracedecoroutinator")
}

android {
    namespace = "com.example.decoroutinatortest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.decoroutinatortest"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                decoroutinatorAndroidProGuardRules()
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
}

stacktraceDecoroutinator {
    artifactTypes = setOf(
        ArtifactTypeDefinition.JAR_TYPE,
        ArtifactTypeDefinition.JVM_CLASS_DIRECTORY,
        ArtifactTypeDefinition.ZIP_TYPE,
        "aar",
        "android-classes-directory",
        "android-classes-jar"
    )
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")
    
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.1")
}

