package com.patproject.test.battleship.data

import android.content.SharedPreferences

inline fun SharedPreferences.editProperty(operation: (SharedPreferences.Editor) -> Unit) {
    val editMe = edit()
    operation(editMe)
    editMe.apply()
}

var SharedPreferences.music
    get() = getBoolean(MUSIC_TAG, true)
    set(value) {
        editProperty {
            it.putBoolean(MUSIC_TAG, value)
        }
    }

var SharedPreferences.sound
    get() = getBoolean(SOUND_TAG, true)
    set(value) {
        editProperty {
            it.putBoolean(SOUND_TAG, value)
        }
    }
const val MUSIC_TAG = "MUSIC"
const val SOUND_TAG = "SOUND"
