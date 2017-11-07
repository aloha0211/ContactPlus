package thomas.alva.contactsplus.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import thomas.alva.contactsplus.R
import thomas.alva.contactsplus.adapter.TabPagerAdapter

class MainActivity : AppCompatActivity() {

    private val imageResIds = arrayOf(R.mipmap.keypad, R.mipmap.call_log, R.mipmap.favorite, R.mipmap.contact)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = TabPagerAdapter(supportFragmentManager, this)
        tabLayout.setupWithViewPager(viewPager)
        for (i in 0 until tabLayout.tabCount) {
            tabLayout.getTabAt(i)?.setIcon(imageResIds[i])
        }
    }
}
