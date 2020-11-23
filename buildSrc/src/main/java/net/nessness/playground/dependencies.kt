package net.nessness.playground

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.1"

    const val timber = "com.jakewharton.timber:timber:4.7.1"

    const val junit = "junit:junit:4.13.1"
    const val robolectric = "org.robolectric:robolectric:4.4"
    const val mockK = "io.mockk:mockk:1.10.2"

    object Mdc {
        const val material = "com.google.android.material:material:1.1.0"
    }

    object Google {
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx:17.2.2"
        const val analytics = "com.google.firebase:firebase-analytics-ktx:17.6.0"
        const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:2.3.0"
        const val gmsGoogleServices = "com.google.gms:google-services:4.3.4"
    }

    object Kotlin {
        private const val version = "1.4.10"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Serialization {
            const val version = "1.0.1"
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:${Kotlin.version}"
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
        }
    }

    object Coroutines {
        private const val version = "1.4.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.5.0-alpha04"
        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha02"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.0-beta01"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha06"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.1.0-alpha01"
        const val swiperefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.2"

        object Lifecycle {
            private const val version = "2.3.0-beta01"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Navigation {
            private const val version = "2.3.1"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Room {
            private const val version = "2.3.0-alpha03"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
        }

        object Paging {
            private const val version = "2.1.2"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
        }

        object Hilt {
            private const val version = "1.0.0-alpha02"
            const val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
        }

        object Test {
            private const val version = "1.3.0"
            const val core = "androidx.test:core:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

        }
    }

    object Hilt {
        private const val version = "2.29.1-alpha"
        const val core = "com.google.dagger:hilt-core:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val test = "com.google.dagger:hilt-android-testing:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterKotlinSeralization = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
        const val mock = "com.squareup.retrofit2:retrofit-mock:$version"
    }

    object OkHttp {
        private const val version = "4.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Groupie {
        private const val version = "2.8.1"
        const val groupie = "com.xwray:groupie:$version"
        const val viewBinding = "com.xwray:groupie-viewbinding:$version"
    }

    object Glide {
        private const val version = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }
}
