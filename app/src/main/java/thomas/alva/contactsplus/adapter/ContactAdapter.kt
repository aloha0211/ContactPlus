package thomas.alva.contactsplus.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import thomas.alva.contactsplus.R
import thomas.alva.contactsplus.model.Contact
import thomas.alva.contactsplus.util.Constant

/**
 * Created by Administrator on 30/10/2017.
 */
class ContactAdapter(private val context: Context, private val contactList: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onBindViewHolder(holder: ContactViewHolder?, position: Int) {
        val contact = contactList[position]
        holder?.tvGroupName?.text = contact.groupName
        holder?.tvContactName?.text = contact.name
        holder?.tvPhoneNumber?.text = contact.number
        if (contact.type == Constant.ACCOUNT_TYPE_PHONE)
            holder?.ivSelectContact?.visibility = View.GONE
        else
            holder?.ivSelectContact?.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int = contactList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContactViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.item_contact, parent)
        return ContactViewHolder(itemView)
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGroupName: TextView = itemView.findViewById(R.id.tvGroupName)
        val tvContactName: TextView = itemView.findViewById(R.id.tvContactName)
        val tvPhoneNumber: TextView = itemView.findViewById(R.id.tvPhoneNumber)
        val ivFavorite: ImageView = itemView.findViewById(R.id.ivFavorite)
        val ivCall: ImageView = itemView.findViewById(R.id.ivCall)
        val ivMessage: ImageView = itemView.findViewById(R.id.ivMessage)
        val ivSelectContact: ImageView = itemView.findViewById(R.id.ivSelectContact)
    }
}