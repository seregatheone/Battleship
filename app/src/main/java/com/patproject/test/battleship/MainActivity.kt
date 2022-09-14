package com.patproject.test.battleship

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.patproject.test.battleship.data.music
import com.patproject.test.battleship.data.sound
import com.patproject.test.battleship.databinding.ActivityMainBinding
import javax.inject.Inject
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        //add toggle button switch
        //add binding service
        super.onStart()
    }

    override fun onDestroy() {
        sharedPreferences.music = binding.toggleButtonMusic.isChecked
        sharedPreferences.sound = binding.toggleButtonSound.isChecked
        super.onDestroy()
    }
}