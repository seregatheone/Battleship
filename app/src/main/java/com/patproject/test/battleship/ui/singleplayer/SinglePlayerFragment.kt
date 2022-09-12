package com.patproject.test.battleship.ui.singleplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.patproject.test.battleship.R
import com.patproject.test.battleship.databinding.FragmentMenuBinding
import com.patproject.test.battleship.databinding.FragmentSinglePlayerBinding

class SinglePlayerFragment : Fragment() {
    private var _binding : FragmentSinglePlayerBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSinglePlayerBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_singlePlayerFragment_to_menuFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}