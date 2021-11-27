package com.example.google_maps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.ads.*
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.AdView







class Admob : AppCompatActivity() {

    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admob)


        val adView = AdView(this)

        adView.adSize = AdSize.BANNER

        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"

        mAdView = findViewById(R.id.adView)
        val but = findViewById<Button>(R.id.button2)
        val adRequest = AdRequest.Builder().addKeyword(AdRequest.DEVICE_ID_EMULATOR)
            .build()
        mAdView?.loadAd(adRequest)

        but.setOnClickListener {
            val intent = Intent(this,AdMobCompose::class.java)
            startActivity(intent)
        }

        mAdView?.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mAdView?.resume()
    }

    override fun onPause() {
        super.onPause()
        mAdView?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdView?.destroy()
    }
}