package com.example.framework.base.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.framework.extension.allowDebugRotation
import com.example.framework.extension.classTag
import com.example.framework.extension.registerFragmentLifecycleCallbacks
import timber.log.Timber

class ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Timber.tag(activity.classTag).d("onCreate()")
        activity.allowDebugRotation()
        activity.registerFragmentLifecycleCallbacks()
    }

    override fun onActivityStarted(activity: Activity) {
        Timber.tag(activity.classTag).d("onStart()")
    }

    override fun onActivityResumed(activity: Activity) {
        Timber.tag(activity.classTag).d("onResume()")
    }

    override fun onActivityPaused(activity: Activity) {
        Timber.tag(activity.classTag).d("onPause()")
    }

    override fun onActivityStopped(activity: Activity) {
        Timber.tag(activity.classTag).d("onStop()")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Timber.tag(activity.classTag).d("onSaveInstanceState()")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Timber.tag(activity.classTag).d("onDestroy()")
    }
}

