package thomas.alva.contactsplus.data

import android.content.Context
import android.provider.ContactsContract
import thomas.alva.contactsplus.model.Contact


/**
 * Created by Administrator on 30/10/2017.
 */
object ContactsManager {

    fun getPhoneContacts(context: Context): List<Contact> {

        val contactList: List<Contact> = arrayListOf()
        val cr = context.contentResolver
        val projection = arrayOf(ContactsContract.RawContacts.ACCOUNT_TYPE, ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME)
        val cur = cr.query(ContactsContract.RawContacts.CONTENT_URI,
                projection, null, null, null)

        if (cur?.count ?: 0 > 0) {
            while (cur != null && cur.moveToNext()) {
                val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val type = cur.getString(cur.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE))
                var phoneNo = ""
                val pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id), null)
                while (pCur!!.moveToNext()) {
                    phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                }
                contactList.plus(Contact(id, name, phoneNo, type))
                pCur.close()
            }
        }
        cur?.close()
        return contactList
    }
}