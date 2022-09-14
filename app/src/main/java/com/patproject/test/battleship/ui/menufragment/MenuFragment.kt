package com.patproject.test.battleship.ui.menufragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.patproject.test.battleship.R
import com.patproject.test.battleship.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewBinding by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_singlePlayerFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}