package com.example.fast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class TabLayoutPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_pager)
        val tablayout = findViewById<TabLayout>(R.id.tablayout)
        val viewpager = findViewById<ViewPager>(R.id.viewpager)
        //tabLayout에  탭을 추가함
        tablayout.addTab(tablayout.newTab().setText("1"))
        tablayout.addTab(tablayout.newTab().setText("2"))
        tablayout.addTab(tablayout.newTab().setText("3"))
        // Pager에  adapter를 장착하는 방법
        viewpager.adapter = FragmentPagerAdapter(supportFragmentManager,3)
        //tablayout과 pager을 연결(둘이 같이 작동되게 붙여줘야함) 시키는코드
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //텝에 현재 포지션에맞게 화면이 돌아가게함
                viewpager.setCurrentItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}
class FragmentPagerAdapter(
    fragmentManager: FragmentManager,
    val tabCount : Int
) : FragmentStatePagerAdapter(fragmentManager){
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return Fragment_First()
            1 -> return Fragment_First()
            else -> return Fragment_First()
        }
    }
}