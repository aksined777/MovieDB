package com.themoviedb.presentation.ui.app

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.themoviedb.presentation.R
import com.themoviedb.presentation.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        switchLanguage(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun switchLanguage(savedInstanceState: Bundle?) {
        savedInstanceState.let { switchLanguage(viewModel.getLanguage()) }
        viewModel.changeEnglishLanguage().observe(this, {
            switchLanguage(it)
        })
        viewModel.handleSwitchLanguage()
    }

    fun switchLanguage(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(
            config,
            resources.displayMetrics
        )
    }
}