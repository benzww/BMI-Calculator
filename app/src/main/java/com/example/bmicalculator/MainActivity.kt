package com.example.bmicalculator



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.bmicalculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_bar_with_fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.fragmentContainer)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.bmisFragment))
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
    }
}