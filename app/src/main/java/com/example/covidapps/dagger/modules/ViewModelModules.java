package com.example.covidapps.dagger.modules;

import androidx.lifecycle.ViewModel;

import com.example.covidapps.dagger.ViewModelKey;
import com.example.covidapps.ui.main.viewModel.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModules {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}
