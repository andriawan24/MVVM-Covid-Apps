package com.example.covidapps.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.covidapps.BaseActivity;
import com.example.covidapps.BaseApplication;
import com.example.covidapps.R;
import com.example.covidapps.ui.main.viewModel.MainViewModel;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    TextView tvSapa, tvDate, tvPositiveToday, tvSembuhToday, tvMeninggalToday
            , tvPositive, tvSembuh, tvMeninggal, tvUpdate;
    TextClock tvJam;

    @Inject
    ViewModelProvider.Factory factory;
    private MainViewModel mainViewModel;

    @Override
    public void onCreateStuff() {
        setContentView(R.layout.activity_main);
        tvSapa = findViewById(R.id.tvSapa);
        tvDate = findViewById(R.id.tvDate);
        tvPositive = findViewById(R.id.tvPositive);
        tvPositiveToday = findViewById(R.id.tvPositiveToday);
        tvMeninggal = findViewById(R.id.tvMeninggal);
        tvMeninggalToday = findViewById(R.id.tvMeninggalToday);
        tvSembuh = findViewById(R.id.tvSembuh);
        tvSembuhToday = findViewById(R.id.tvSembuhToday);
        tvUpdate = findViewById(R.id.tvUpdate);
        tvJam = findViewById(R.id.tvJam);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        tvDate.setText(format.format(Calendar.getInstance().getTime()));

        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        getSapaan();
        getDataCountry();
        getLoading();
    }

    private void getSapaan() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= 1 && hour <= 11) {
            tvSapa.setText("Selamat Pagi");
        }
    }

    private void getDataCountry() {
        mainViewModel.getData()
                .observe(this, data -> {
                    tvMeninggal.setText(NumberFormat.getInstance().format(data.getDeaths()));
                    tvMeninggalToday.setText(NumberFormat.getInstance().format(data.getTodayDeaths()));
                });
    }

    private void getLoading() {
        mainViewModel.getIsLoading()
                .observe(this, isLoading -> {
                    if (isLoading) {
                        showProgressDialog("Main", "Mohon Menunggu..");
                    } else {
                        dismissProgressDialog();
                    }
                });
    }

    @Override
    public void initUi() {

    }
}