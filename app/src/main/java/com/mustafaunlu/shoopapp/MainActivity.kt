package com.mustafaunlu.shoopapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mustafaunlu.shoopapp.databinding.ActivityMainBinding
import com.mustafaunlu.shoopapp.utils.gone
import com.mustafaunlu.shoopapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.myToolbar, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.myToolbar.visible()
                    binding.myToolbar.title = getString(R.string.products_list_appbar_title)
                    binding.myToolbar.navigationIcon = null
                }
                R.id.productDetailFragment -> {
                    binding.myToolbar.visible()
                    binding.myToolbar.title = getString(R.string.product_detail_appbar_title)
                }
                R.id.shoppingListFragment -> {
                    binding.myToolbar.visible()
                    binding.myToolbar.title = getString(R.string.shopping_list_appbar_title)
                }
                else -> {
                    binding.myToolbar.gone()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                navHostFragment.navController.navigate(R.id.shoppingListFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
