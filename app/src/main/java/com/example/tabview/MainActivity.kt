package com.example.tabview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(FirstFragment(), "First Tab")
        adapter.addFragment(SecondFragment(), "Second Tab")
        adapter.addFragment(ThirdFragment(), "Third Tab")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.customView?.setBackgroundColor(resources.getColor(R.color.orange))
                val textView = tab.customView?.findViewById<TextView>(R.id.tabTextView)
                textView?.setTextColor(Color.WHITE)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.customView?.setBackgroundColor(resources.getColor(R.color.white))
                val textView = tab.customView?.findViewById<TextView>(R.id.tabTextView)
                textView?.setTextColor(Color.BLACK)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Do nothing
            }
        })

        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            tab?.customView = createTabView(adapter.getPageTitle(i).toString())
        }
    }
        private fun createTabView(tabTitle: String): View {
            val tabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null)
            val textView = tabView.findViewById<TextView>(R.id.tabTextView)
            textView.text = tabTitle
            return tabView
    }
}