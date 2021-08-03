package com.schratzenstaller.wilcilene.projeto_integrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var tlMovies: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vpMovies = findViewById<ViewPager2>(R.id.vpMovies)
        tlMovies = findViewById(R.id.tlMovies)

        val adapter = VpagerAdapterMovies(supportFragmentManager, lifecycle)
        vpMovies.adapter = adapter
        TabLayoutMediator(tlMovies, vpMovies, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                when (position) {
                    1 -> tab.text = getString(R.string.favorites)
                    else -> tab.text = getString(R.string.all_movies)
                }
            }
        }).attach()
    }
}