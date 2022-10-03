package com.patproject.test.battleship

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.patproject.test.battleship.data.shared_preferences.music
import com.patproject.test.battleship.data.shared_preferences.sound
import com.patproject.test.battleship.databinding.ActivityMainBinding
import com.patproject.test.battleship.services.MusicService
import javax.inject.Inject
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    private var boundMusicService : MusicService? = null

    private var isBound = false

    @Inject
    lateinit var sharedPreferences : SharedPreferences

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            isBound = true
            boundMusicService = (binder as? MusicService.LocalBinder)!!.getService()

            with(binding) {
                toggleButtonMusic.setOnCheckedChangeListener { _, isChecked ->
                    when (isChecked) {
                        true -> boundMusicService!!.startMusic()
                        false -> boundMusicService!!.stopMusic()
                    }
                }
                toggleButtonMusic.isChecked = sharedPreferences.music
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            boundMusicService = null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {

        val intent = Intent(this,MusicService::class.java)
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)

        with(binding){
            toggleButtonSound.isChecked = sharedPreferences.sound
        }
        super.onStart()
    }

    override fun onStop() {
        sharedPreferences.music = binding.toggleButtonMusic.isChecked
        sharedPreferences.sound = binding.toggleButtonSound.isChecked
        super.onStop()
    }

    override fun onDestroy() {
        if (isBound) {
            boundMusicService!!.stopMusic()
            unbindService(serviceConnection)
            isBound = false
        }
        super.onDestroy()
    }
}