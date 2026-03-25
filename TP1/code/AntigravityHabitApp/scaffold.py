import os

base_dir = r"c:\Users\zisaj\Documents\LEIM\SEM6\DAM\code\AntigravityHabitApp"

directories = [
    "app/src/main/java/com/example/habittracker",
    "app/src/main/java/com/example/habittracker/data/database",
    "app/src/main/java/com/example/habittracker/data/repository",
    "app/src/main/java/com/example/habittracker/di",
    "app/src/main/java/com/example/habittracker/domain/model",
    "app/src/main/java/com/example/habittracker/ui/common",
    "app/src/main/java/com/example/habittracker/ui/main",
    "app/src/main/java/com/example/habittracker/ui/add",
    "app/src/main/res/layout",
    "app/src/main/res/values",
    "app/src/main/res/values-night",
    "app/src/main/res/xml"
]

for d in directories:
    os.makedirs(os.path.join(base_dir, d), exist_ok=True)

files = {}

files["settings.gradle.kts"] = """pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "HabitTracker"
include(":app")
"""

files["build.gradle.kts"] = """// Top-level build file
plugins {
    id("com.android.application") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
}
"""

files["gradle.properties"] = """org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
android.nonTransitiveRClass=true
"""

files["app/build.gradle.kts"] = """plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.habittracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.habittracker"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Desugaring for java.time on SDK < 26
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // UI & Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51")
    ksp("com.google.dagger:hilt-android-compiler:2.51")
}
"""

files["app/src/main/AndroidManifest.xml"] = """<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:name=".HabitTrackerApplication"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.HabitTracker"
        tools:targetApi="31">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@style/Theme.HabitTracker">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
"""

files["app/src/main/res/values/strings.xml"] = """<resources>
    <string name="app_name">Habit Tracker</string>
</resources>
"""

files["app/src/main/res/values/themes.xml"] = """<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Base.Theme.HabitTracker" parent="Theme.Material3.DayNight.NoActionBar">
        <!-- Customize your light theme here. -->
    </style>
    <style name="Theme.HabitTracker" parent="Base.Theme.HabitTracker" />
</resources>
"""

files["app/src/main/res/values-night/themes.xml"] = """<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Base.Theme.HabitTracker" parent="Theme.Material3.DayNight.NoActionBar">
        <!-- Customize your dark theme here. -->
    </style>
</resources>
"""

files["app/src/main/res/xml/backup_rules.xml"] = """<?xml version="1.0" encoding="utf-8"?><full-backup-content><include domain="sharedpref" path="."/><include domain="database" path="."/><include domain="file" path="."/></full-backup-content>"""

files["app/src/main/res/xml/data_extraction_rules.xml"] = """<?xml version="1.0" encoding="utf-8"?><data-extraction-rules><cloud-backup><include domain="sharedpref" path="."/><include domain="database" path="."/><include domain="file" path="."/></cloud-backup><device-transfer><include domain="sharedpref" path="."/><include domain="database" path="."/><include domain="file" path="."/></device-transfer></data-extraction-rules>"""


files["app/src/main/java/com/example/habittracker/HabitTrackerApplication.kt"] = """package com.example.habittracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Hilt Application class, required for Hilt dependency injection setup
@HiltAndroidApp
class HabitTrackerApplication : Application()
"""

files["app/src/main/java/com/example/habittracker/MainActivity.kt"] = """package com.example.habittracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

// Main Activity serving as the container for fragments
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
"""

files["app/src/main/res/layout/activity_main.xml"] = """<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!-- Fragment container for navigation component -->
    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
"""

files["app/src/main/res/navigation/nav_graph.xml"] = """<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/habitListFragment">

    <!-- Navigation placeholder -->

</navigation>
"""

os.makedirs(os.path.join(base_dir, "app/src/main/res/navigation"), exist_ok=True)

for path, content in files.items():
    full_path = os.path.join(base_dir, path)
    with open(full_path, "w", encoding="utf-8") as f:
        f.write(content)

print("Scaffolding created successfully.")
