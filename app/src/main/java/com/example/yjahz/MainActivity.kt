package com.example.yjahz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.yjahz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        hideToolbar(navController)
    }
    private fun hideToolbar(navController: NavController){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(
                destination.id == R.id.signup||destination.id == R.id.login) {
                binding?. toolbar?.toolbar?.visibility = View.GONE
            }else {
                binding?. toolbar?.toolbar?.visibility = View.VISIBLE
            }
        }
    }
}