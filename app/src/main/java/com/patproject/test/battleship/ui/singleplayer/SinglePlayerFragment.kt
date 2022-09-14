package com.patproject.test.battleship.ui.singleplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.patproject.test.battleship.R
import com.patproject.test.battleship.databinding.FragmentSinglePlayerBinding

class SinglePlayerFragment : Fragment(R.layout.fragment_single_player) {
    private val viewBinding by viewBinding(FragmentSinglePlayerBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.button.setOnClickListener {
            findNavController().navigate(R.id.action_singlePlayerFragment_to_menuFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}