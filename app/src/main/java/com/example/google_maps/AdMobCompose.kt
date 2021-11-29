package com.example.google_maps

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.google_maps.ui.theme.Google_MapsTheme
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG

class AdMobCompose : ComponentActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "MainActivityTVFL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Google_MapsTheme {
                Surface(color = MaterialTheme.colors.background) {

                    val adRequest = AdRequest.Builder().build()

                    InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
                        override fun onAdFailedToLoad(adError: LoadAdError) {
                            Log.d(TAG, adError.message)
                            mInterstitialAd = null
                            mInterstitialAd?.show(this@AdMobCompose)
                        }

                        override fun onAdLoaded(interstitialAd: InterstitialAd) {
                            Log.d(TAG, "Ad was loaded.")
                            mInterstitialAd = interstitialAd
                            mInterstitialAd?.show(this@AdMobCompose)
                        }
                    })
                    AdvertView()
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun AdvertView(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            AndroidView(
                modifier = modifier.fillMaxWidth(),
                factory = { context ->
                    AdView(context).apply {
                        adSize = AdSize.BANNER
                        adUnitId = "ca-app-pub-3940256099942544/6300978111"
                        loadAd(AdRequest.Builder().build())
                    }
                },
                update = {
                    it.apply {
                        loadAd(AdRequest.Builder().build())
                    }
                    it.adListener = object : AdListener(){
                        override fun onAdLoaded() {
                            // Code to be executed when an ad finishes loading.
                            Toast.makeText(context,"onAdLoaded",Toast.LENGTH_LONG).show()
                        }

                        override fun onAdFailedToLoad(adError : LoadAdError) {
                            // Code to be executed when an ad request fails.
                            Toast.makeText(context,"onAdFailedToLoad",Toast.LENGTH_LONG).show()
                        }

                        override fun onAdOpened() {
                            // Code to be executed when an ad opens an overlay that
                            // covers the screen.
                            Toast.makeText(context,"onAdOpened",Toast.LENGTH_LONG).show()
                        }

                        override fun onAdClicked() {
                            // Code to be executed when the user clicks on an ad.
                            Toast.makeText(context,"onAdClicked",Toast.LENGTH_LONG).show()
                        }

                        override fun onAdClosed() {
                            // Code to be executed when the user is about to return
                            // to the app after tapping on an ad.
                            Toast.makeText(context,"onAdClosed",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            )
        }
    ) {
    }
}