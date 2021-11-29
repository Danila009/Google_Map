package com.example.google_maps

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.google_maps.ui.theme.Google_MapsTheme
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import javax.sql.DataSource

class VideoAct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Google_MapsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    VideoFun()
                }
            }
        }
    }
}

@Composable
fun VideoFun() {

    val content = LocalContext.current

    val exoPlayer = remember (content){
        com.google.android.exoplayer2.SimpleExoPlayer.Builder(content).build().apply {

            val dataSourceFactory = DefaultDataSourceFactory(content,
                Util.getUserAgent(content, content.packageName))

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(
                    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                ))
            this.prepare(source)
        }
    }

    AndroidView(factory = { ctx ->
        PlayerView(ctx).apply {
            player = exoPlayer
        }
    })
}