package com.patproject.test.battleship.mainactivity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.patproject.test.battleship.R
import com.patproject.test.battleship.appComponent
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
        binding.toggleButtonMusic.isChecked = sharedPreferences.music
        binding.toggleButtonSound.isChecked = sharedPreferences.sound
        super.onStart()
    }

    override fun onStop() {
        sharedPreferences.music = binding.toggleButtonMusic.isChecked
        sharedPreferences.sound = binding.toggleButtonSound.isChecked
        super.onStop()
    }
}