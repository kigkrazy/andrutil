package com.reizx.util.di.component;

import android.app.Activity;

import com.reizx.util.di.module.ActivityModule;
import com.reizx.util.di.scope.ActivityScope;
import com.reizx.util.view.MainActivity;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    
    void inject(MainActivity mainActivity);
}
