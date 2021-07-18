package com.example.graphsexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {
    AdView adView;
    InterstitialAd ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adView = findViewById(R.id.adView);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull  InitializationStatus initializationStatus) {

            }
        });
        AdRequest request = new AdRequest.Builder().build();
        adView.loadAd(request);
        InterstitialAd.load(this, "ca-app-pub-3940256099942544/2247696110", request, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull  InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                ad = interstitialAd;
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError){
                super.onAdFailedToLoad(loadAdError);
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void bar(View view) {
        startActivity(new Intent(this,BarActivity.class));
    }

    public void pie(View view) {
        startActivity(new Intent(this,PieActivity.class));
    }

    public void show(View view) {
        if(ad!=null){
            ad.show(this);
        }else{
            Toast.makeText(this, "Not Yet Loaded", Toast.LENGTH_SHORT).show();
        }
    }
}