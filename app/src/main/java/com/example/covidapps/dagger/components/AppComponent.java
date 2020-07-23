package com.example.covidapps.dagger.components;

import com.example.covidapps.dagger.modules.ApiModules;
import com.example.covidapps.dagger.modules.ContextModules;
import com.example.covidapps.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModules.class, ContextModules.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
