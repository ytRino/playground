package net.nessness.playground.lifecycle

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import timber.log.Timber

class ActivityLifecycleCallbacks(
        private val tag: String = "lifecycle",
        private val onCreated: (activity: Activity, savedInstanceState: Bundle?) -> Unit = { _, _ -> },
        private val onStarted: (activity: Activity) -> Unit = {},
        private val onResumed: (activity: Activity) -> Unit = {},
        private val onPaused: (activity: Activity) -> Unit = {},
        private val onStopped: (activity: Activity) -> Unit = {},
        private val onSaveInstanceState: (activity: Activity, outState: Bundle?) -> Unit = { _, _ -> },
        private val onDestroyed: (activity: Activity) -> Unit = {}
) : Application.ActivityLifecycleCallbacks {

    var enableLog = true

    private fun logEvent(activity: Activity, event: String) {
        if (enableLog) Timber.tag(tag).v("$event: $activity")
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        logEvent(activity, "A/Created")
        onCreated(activity, savedInstanceState)
    }

    override fun onActivityStarted(activity: Activity) {
        logEvent(activity, "A/Started")
        onStarted(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        logEvent(activity, "A/Resumed")
        onResumed(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        logEvent(activity, "A/Paused")
        onPaused(activity)
    }

    override fun onActivityStopped(activity: Activity) {
        logEvent(activity, "A/Stopped")
        onStopped(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        logEvent(activity, "A/SaveInstanceState")
        onSaveInstanceState(activity, outState)
    }

    override fun onActivityDestroyed(activity: Activity) {
        logEvent(activity, "A/Destroyed")
        onDestroyed(activity)
    }
}

class FragmentLifecycleCallbacks(
        private val tag: String = "lifecycle",
        private val onPreAttached: (fm: FragmentManager, f: Fragment) -> Unit = { _, _ -> },
        private val onAttached: (fm: FragmentManager, f: Fragment) -> Unit = { _, _ -> },
        private val onPreCreated: (fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) -> Unit = { _, _, _ -> },
        private val onCreated: (fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) -> Unit = { _, _, _ -> },
        private val onViewCreated: (fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) -> Unit = { _, _, _, _ -> },
        private val onActivityCreated: (fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) -> Unit = { _, _, _ -> },
        private val onStarted: (fm: FragmentManager, f: Fragment) -> Unit = { _, _ -> },
        private val onResumed: (fm: FragmentManager, f: Fragment) -> Unit = { _, _ -> },
        private val onPaused: (fm: FragmentManager, f: Fragment) -> Unit = { _, _ -> },
        private val onStopped: (fm: FragmentManager, f: Fragment) -> Unit = { _, _ -> },
        private val onSaveInstanceState: (fm: FragmentManager, f: Fragment, outState: Bundle) -> Unit = { _, _, _ -> },
        private val onViewDestroyed: (fm: FragmentManager, f: Fragment) -> Unit = { _, _ -> },
        private val onDestroyed: (fm: FragmentManager, f: Fragment) -> Unit = { _, _ -> },
        private val onDetached: (fm: FragmentManager, f: Fragment) -> Unit = { _, _ -> }
) : FragmentManager.FragmentLifecycleCallbacks() {

    var enableLog = true

    private fun logEvent(fragment: Fragment, event: String) {
        if (enableLog) Timber.tag(tag).v("$event: $fragment")
    }

    override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
        logEvent(f, "F/PreAttached")
        onPreAttached(fm, f)
    }

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        logEvent(f, "F/Attached")
        onAttached(fm, f)
    }

    override fun onFragmentPreCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        logEvent(f, "F/PreCreated")
        onPreCreated(fm, f, savedInstanceState)
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        logEvent(f, "F/Created")
        onCreated(fm, f, savedInstanceState)
    }

    override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
        logEvent(f, "F/ViewCreated")
        onViewCreated(fm, f, v, savedInstanceState)
    }

    override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        logEvent(f, "F/ActivityCreated")
        onActivityCreated(fm, f, savedInstanceState)
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        logEvent(f, "F/Started")
        onStarted(fm, f)
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        logEvent(f, "F/Resumed")
        onResumed(fm, f)
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        logEvent(f, "F/Paused")
        onPaused(fm, f)
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        logEvent(f, "F/Stopped")
        onStopped(fm, f)
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        logEvent(f, "F/SaveInstanceState")
        onSaveInstanceState(fm, f, outState)
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        logEvent(f, "F/ViewDestroyed")
        onViewDestroyed(fm, f)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        logEvent(f, "F/Destroyed")
        onDestroyed(fm, f)
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        logEvent(f, "F/Detached")
        onDetached(fm, f)
    }
}
