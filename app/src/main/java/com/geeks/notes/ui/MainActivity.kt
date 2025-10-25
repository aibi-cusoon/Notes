package com.geeks.notes.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.geeks.notes.data.models.local.Pref
import com.geeks.notes.R
import com.geeks.notes.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var pref: Pref
    private val userFirebase = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        pref = Pref(this)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val navHost: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navGraph = navHost.navController.navInflater.inflate(R.navigation.nav_graph)

        navGraph.setStartDestination(
            if (pref.isUserSeen()) {
                if (userFirebase!== null){
                R.id.mainFragment
                }else{
                    R.id.authFragment
                }
            } else{
                R.id.onBoardFragment
            }


        )
        navHost.navController.graph = navGraph

    }
}