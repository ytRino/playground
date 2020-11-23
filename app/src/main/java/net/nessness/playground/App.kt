package net.nessness.playground

import android.app.Application
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.HiltAndroidApp
import net.nessness.playground.lifecycle.ActivityLifecycleCallbacks
import net.nessness.playground.lifecycle.FragmentLifecycleCallbacks
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        logLifecycle()
    }

    private fun logLifecycle() {
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks(
            onCreated = { activity, _ ->
                (activity as? FragmentActivity)
                    ?.supportFragmentManager
                    ?.registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks(), true)
            }
        ))
    }
}
