package thomas.alva.contactsplus.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_contacts.*
import thomas.alva.contactsplus.R
import thomas.alva.contactsplus.adapter.ContactAdapter
import thomas.alva.contactsplus.data.ContactsManager
import thomas.alva.contactsplus.model.Contact

class ContactsFragment : Fragment() {

    private var adapter: ContactAdapter? = null
    private var contactList: List<Contact> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ContactAdapter(context, contactList)
        recyclerView.adapter = adapter
        activity.runOnUiThread {
            contactList = ContactsManager.getPhoneContacts(context!!)
            adapter?.notifyDataSetChanged()
        }
    }
}
