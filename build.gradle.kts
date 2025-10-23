// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("androidx.navigation.safeargs.kotlin") apply false version "2.9.5"
    id("com.google.devtools.ksp") version "2.2.20-2.0.2"
}