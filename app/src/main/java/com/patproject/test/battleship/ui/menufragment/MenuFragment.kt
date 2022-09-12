package com.patproject.test.battleship.ui.menufragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.patproject.test.battleship.R
import com.patproject.test.battleship.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding : FragmentMenuBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_singlePlayerFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}