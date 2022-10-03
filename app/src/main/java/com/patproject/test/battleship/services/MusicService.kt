package com.patproject.test.battleship.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.patproject.test.battleship.R

class MusicService : Service() {
    private var musicPlayer: MediaPlayer? = null

    private val binder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class LocalBinder : Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }
    }

    private fun initialize() {
        musicPlayer = MediaPlayer.create(this, R.raw.battle_music)
    }

    fun startMusic() {
        if (musicPlayer == null) {
            initialize()
            musicPlayer!!.start()
        } else {
            musicPlayer!!.start()
        }
    }

    fun stopMusic() {
        if (musicPlayer!= null) {
            musicPlayer!!.stop()
            musicPlayer!!.release()
            musicPlayer= null
        }
    }
    fun isPlaying():Boolean{
        return musicPlayer!!.isPlaying
    }

}