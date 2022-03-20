package com.themoviedb.presentation.extension

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.themoviedb.presentation.R

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.showSnackBar(
    text: String,
    idActionText: Int,
    lenght: Int = Snackbar.LENGTH_INDEFINITE,
    action: (View) -> Unit
) {
    Snackbar.make(
        this, text, lenght
    )
        .setAction(resources.getText(idActionText).toString()) {
            action(this)
        }.show()
}

fun View.showSnackBarSucess(
    idText: Int,
    lenght: Int = Snackbar.LENGTH_INDEFINITE,
) {
    Snackbar.make(
        this, resources.getText(idText), lenght
    ).show()
}

