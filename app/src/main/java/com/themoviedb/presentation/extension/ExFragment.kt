package com.themoviedb.presentation.extension

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.themoviedb.presentation.R

fun Fragment.findNavController(): NavController =
    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)