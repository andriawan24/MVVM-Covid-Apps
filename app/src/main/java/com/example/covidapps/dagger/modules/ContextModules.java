package com.example.covidapps.dagger.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ContextModules {
    private final Context context;

    public ContextModules(Context context) {
        this.context = context;
    }

    @Binds
    abstract Context bindContext(Application application);
}
