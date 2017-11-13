package thomas.alva.contactsplus.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import thomas.alva.contactsplus.util.Constant

/**
 * Created by Administrator on 30/10/2017.
 */
class Contact(var id: String? = "", var name: String? = "", var number: String? = "", var type: String? = Constant.ACCOUNT_TYPE_PHONE, var photoUri: Uri? = null) : Comparable<Contact>, Parcelable {

    var groupName: String? = ""

    var isEndGroup: Boolean = false

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(String::class.java.classLoader) as String
        name = parcel.readString()
        number = parcel.readString()
        type = parcel.readString()
        groupName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        parcel?.writeString(id)
        parcel?.writeString(name)
        parcel?.writeString(number)
        parcel?.writeString(type)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }

    override fun compareTo(other: Contact): Int {
        return name!!.compareTo(other.name!!)
    }
}