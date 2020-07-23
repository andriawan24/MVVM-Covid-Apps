package com.example.covidapps.ui.main.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covidapps.ui.main.mainService.MainService;
import com.example.covidapps.ui.main.model.ResponseCountry;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    public MainService mainService;

    @Inject
    MainViewModel(MainService mainService) {
        this.mainService = mainService;
    }

    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<ResponseCountry> data = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();

    public MutableLiveData<ResponseCountry> getData() {
        isLoading.postValue(true);
        loadData();
        return data;
    }

    private void loadData() {
        disposable.add(mainService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<ResponseCountry>() {
                @Override
                public void onSuccess(ResponseCountry responseCountry) {
                    isLoading.postValue(false);
                    data.setValue(responseCountry);
                }

                @Override
                public void onError(Throwable e) {
                    isLoading.postValue(false);
                    message.postValue(e.getMessage());
                }
            }));
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}
