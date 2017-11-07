package thomas.alva.contactsplus.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import thomas.alva.contactsplus.R
import thomas.alva.contactsplus.fragment.ContactsFragment
import thomas.alva.contactsplus.fragment.FavoritesFragment

/**
 * Created by Administrator on 28/10/2017.
 */
class TabPagerAdapter(fm : FragmentManager, context : Context) : FragmentPagerAdapter(fm) {

    private val PAGE_COUNT = 4
    private val tabTitles = arrayOf(context.getString(R.string.keypad),
            context.getString(R.string.logs),
            context.getString(R.string.favorites),
            context.getString(R.string.contacts))

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> FavoritesFragment()
        1 -> ContactsFragment()
        2 -> FavoritesFragment()
        3 -> ContactsFragment()
        else -> Fragment()
    }

    override fun getCount() = PAGE_COUNT

    override fun getPageTitle(position: Int): String = tabTitles[position]
}