package thomas.alva.contactsplus.data

import android.content.Context
import android.provider.ContactsContract
import thomas.alva.contactsplus.model.Contact
import java.util.*


/**
 * Created by Administrator on 30/10/2017.
 */
object ContactsManager {

    fun getPhoneContacts(context: Context): MutableList<Contact> {

        val contacts: MutableList<Contact> = mutableListOf()
        val cursor = context.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
//        val photoIdIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID)
        cursor.moveToFirst()
        do {
            val idContact = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID))
            val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val type = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE))
            contacts.add(Contact(idContact, name, phoneNumber, type))
            //...
        } while (cursor.moveToNext())
        cursor.close()
        Collections.sort(contacts)
        var groupValue = ""
        for(i: Int in 0 until contacts.size) {
            val contact = contacts[i]
            val groupName = contact.name?.substring(0, 1)
            if (groupName != groupValue) {
                contact.groupName = groupName
                groupValue = groupName!!
                if (i > 0)
                    contacts[i - 1].isEndGroup = true
            }
        }
        return contacts
    }
}