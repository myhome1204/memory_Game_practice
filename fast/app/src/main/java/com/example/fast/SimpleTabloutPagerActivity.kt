package com.example.fast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class SimpleTabloutPagerActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_tablout_pager)
        val tabLayout = findViewById<TabLayout>(R.id.simpletablayout)
        //여기서는 viewpager2는 못쓰는거같다
        val viewPager = findViewById<ViewPager>(R.id.simpleviewpager)
        tabLayout.addTab(tabLayout.newTab().setText("1"))
        tabLayout.addTab(tabLayout.newTab().setText("2"))
        tabLayout.addTab(tabLayout.newTab().setText("3"))

        viewPager.adapter = ViewPagerAdapter(LayoutInflater.from(this),3)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //텝에 현재 포지션에맞게 화면이 돌아가게함
                viewPager.setCurrentItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}
class ViewPagerAdapter(
    val layoutInflater : LayoutInflater,
    val tabCount : Int
): PagerAdapter(){
    override fun getCount(): Int {
        return tabCount
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view ===`object` as View
        // === 은 오른쪽왼쪽이 같은지 다른지를 비교하는건맞지만 주소값이 같은지를 비교함
        //너의 화면에보이는뷰랑 너가 아래서만든뷰랑 같아? 를물어보는 메소드이다

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //여기함수에서 container가 들어온다 그 container에 view를 붙여아한다
        //내생각에는 Adapter를달아줄때 그 Adapter를 달아주는 view가 container인거같다
        //보여줄 item 을 return
        when(position){
            0 -> {
                val view = layoutInflater.inflate(R.layout.first_fragment,container,false)
                container.addView(view)
                return view
            }
            1 -> {
                val view = layoutInflater.inflate(R.layout.car_item,container,false)
                container.addView(view)
                return view
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.first_fragment,container,false)
                container.addView(view)
                return view
            }
        }
    }
}