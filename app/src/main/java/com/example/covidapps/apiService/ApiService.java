package com.example.covidapps.apiService;

import com.example.covidapps.ui.main.model.ResponseCountry;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("countries/indonesia")
    Single<ResponseCountry> getDataCountry();
}
