package com.gromyk.compose.playground.ui.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gromyk.compose.playground.R
import com.gromyk.compose.playground.ui.config.components.HomeScreen

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(context = requireContext()).apply {
            setContent {
                HomeScreen {
                    val args = bundleOf("newsId" to it.newsId)
                    findNavController().navigate(R.id.openDetails, args)
                }
            }
        }
    }
}