package com.github.syl20lego;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

public class ToastApplicationListener implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        Toast.makeText(activity.getApplicationContext(), "TEST", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
