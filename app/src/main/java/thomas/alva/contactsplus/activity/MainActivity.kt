package thomas.alva.contactsplus.activity

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import thomas.alva.contactsplus.R
import thomas.alva.contactsplus.adapter.TabPagerAdapter
import thomas.alva.contactsplus.data.ContactsManager
import thomas.alva.contactsplus.fragment.ContactsFragment
import thomas.alva.contactsplus.model.Contact

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private val imageResIds = arrayOf(R.mipmap.keypad, R.mipmap.call_log, R.mipmap.favorite, R.mipmap.contact)

    private var tabPagerAdapter: TabPagerAdapter? = null

    private var contactList : MutableList<Contact> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabPagerAdapter = TabPagerAdapter(supportFragmentManager, this)
        viewPager.adapter = tabPagerAdapter
        viewPager.setPagingEnabled(false);
        viewPager.addOnPageChangeListener(this)
        tabLayout.setupWithViewPager(viewPager)
        for (i in 0 until tabLayout.tabCount) {
            tabLayout.getTabAt(i)?.setIcon(imageResIds[i])
        }
        GetPhoneContactsTask().execute()
    }

    override fun onPageSelected(position: Int) {
        if (position == 3 && contactList.isNotEmpty()) {
            val contactsFragment = tabPagerAdapter?.getFragment(position) as ContactsFragment
            contactsFragment.setContactList(contactList)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    internal inner class GetPhoneContactsTask : AsyncTask<Any, Any, MutableList<Contact>>() {
        override fun doInBackground(vararg params: Any?): MutableList<Contact> = ContactsManager.getPhoneContacts(this@MainActivity)

        override fun onPostExecute(result: MutableList<Contact>?) {
            super.onPostExecute(result)
            contactList.clear()
            contactList.addAll(result!!)
        }
    }
}
