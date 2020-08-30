package com.gromyk.compose.playground.ui.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gromyk.compose.playground.Router
import com.gromyk.compose.playground.Screen
import com.gromyk.compose.playground.data.ItemData
import com.gromyk.compose.playground.ui.config.components.DetailsScreen

class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = arguments?.getInt("newsId") ?: error("Can't proceed without news id")
        return ComposeView(context = requireContext()).apply {
            setContent {
                val data = ItemData(id, "Name + $id", id.toString())
                DetailsScreen(
                    data,
                    object : Router {
                        override fun navigate(destination: Screen) = TODO("bruh, no idea what 2 do")

                        override fun back() {
                            findNavController().popBackStack()
                        }
                    }
                )
            }
        }
    }
}