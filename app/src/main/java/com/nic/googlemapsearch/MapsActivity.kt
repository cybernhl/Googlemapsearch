package com.nic.googlemapsearch

import android.Manifest
import android.location.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

import com.nic.googlemapsearch.databinding.ActivityMapsBinding
import io.realm.Realm
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

@RequiresApi(Build.VERSION_CODES.R)
class MapsActivity(

) : AppCompatActivity() {
    val TAG = MapsActivity::class.java.name
    private lateinit var binding: ActivityMapsBinding
  private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        realm = Realm.getDefaultInstance()



//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_bottem_navigation) as NavHostFragment
//        val navController = navHostFragment.navController
        val navController = findNavController(R.id.nav_host_fragment_activity_bottem_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
      //  navController.navigate(R.id.navigation_homfragemnt)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_homfragemnt,
             R.id.navigation_adddatafragment, R.id.navigation_listfragment))
        setupActionBarWithNavController(navController, appBarConfiguration)









    }

    override fun onStart() {


        super.onStart()
    }







    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            with(it) {


                when {
                    it.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                        Log.e("TAG", ":ACCESS_FINE_LOCATION ")

                    }
                    it.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {

                        Log.e("TAG", ":ACCESS_COARSE_LOCATION ")

                        // Only approximate location access granted.
                    }
                    else -> {

                        Log.e("TAG", ": no Permission granyed")
                        // No location access granted.
                    }
                }

            }


        }












    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menu?.findItem(R.id.deleteitom)?.setVisible(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home ->

                onBackPressed()

            R.id.deleteitom -> {

               Utill(applicationContext).Deletaitem(applicationContext,realm)
            //                {
//                 Utill(applicationContext).showmassage(this,binding.container,"All Task list is Deleted ")
//                }

            }


        }
        return true
    }


    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}


