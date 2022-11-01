package com.patproject.test.battleship.ui.placement

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.patproject.test.battleship.R
import com.patproject.test.battleship.databinding.FragmentPlacementStageBinding

class PlacementStageFragment : Fragment(R.layout.fragment_placement_stage) {
    private val viewBinding by viewBinding(FragmentPlacementStageBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.startGame.setOnClickListener {
            findNavController().navigate(R.id.action_placementStageFragment_to_singlePlayerFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}