package com.features.growharvest.NavigationBar

// MainActivity.kt
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.features.growharvest.Fragment.AccountFrag
//import com.features.growharvest.DataProduct.DataProductFrag
import com.features.growharvest.Fragment.HomeFrag
import com.features.growharvest.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavbarMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_bar)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val username = getUsernameFromSharedPreferences()



        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> showFragment(HomeFrag())
                //"home" -> replaceFragment(HomeFragment.newInstance(getUsernameFromSharedPreferences()))
//                R.id.menu_product -> showFragment(DataProductFrag())
                R.id.menu_account -> showFragment(AccountFrag())
            }
            true
        }

        initRecyclerView()
        // Default fragment
        if (savedInstanceState == null) {
            showFragment(HomeFrag())
        }
    }

    private fun getUsernameFromSharedPreferences(): String {
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "") ?: ""
    }
    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }
    private fun initRecyclerView() {}
}

