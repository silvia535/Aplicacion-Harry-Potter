package es.uva.inf5g.psm.hpapp.servicios

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import es.uva.inf5g.psm.hpapp.R

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.harry_potter_music).apply {
            isLooping = true
            start()
        }
    }

    override fun onDestroy() {
        mediaPlayer.stop()
        mediaPlayer.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}