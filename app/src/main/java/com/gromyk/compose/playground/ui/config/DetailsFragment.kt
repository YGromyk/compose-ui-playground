package com.gromyk.compose.playground.ui.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.gromyk.compose.playground.data.ItemData
import com.gromyk.compose.playground.ui.config.components.DetailsScreen

class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = arguments?.let { DetailsFragmentArgs.fromBundle(it).newsId }
            ?: error("Can't proceed without news id")
        return ComposeView(context = requireContext()).apply {
            setContent {
                DetailsScreen(ItemData(id, "Name + $id", id.toString()))
            }
        }
    }
}