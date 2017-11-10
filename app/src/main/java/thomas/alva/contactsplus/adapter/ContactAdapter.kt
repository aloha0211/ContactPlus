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

/**
 * Created by Administrator on 30/10/2017.
 */
class ContactAdapter(private val context: Context, private val contactList: MutableList<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onBindViewHolder(holder: ContactViewHolder?, position: Int) {
        val contact = contactList[position]
        holder?.tvGroupName?.text = contact.groupName
        holder?.tvContactName?.text = contact.name
        holder?.tvPhoneNumber?.text = contact.number

        if (contact.groupName?.isNotBlank()!!) {
            holder?.tvGroupName?.visibility = View.VISIBLE
            holder?.tvGroupName?.text = contact.groupName
        } else {
            holder?.tvGroupName?.visibility = View.GONE
        }

        if (contact.isEndGroup) {
            holder?.lineHorizontal?.visibility = View.VISIBLE
        } else {
            holder?.lineHorizontal?.visibility = View.GONE
        }

//        when (contact.type) {
//            Constant.ACCOUNT_TYPE_PHONE -> holder?.ivContactAvatar?.setImageResource(R.drawable.ic_device)
//            Constant.ACCOUNT_TYPE_SIM -> holder?.ivContactAvatar?.setImageResource(R.drawable.ic_sim1)
//            Constant.ACCOUNT_TYPE_SIM2 -> holder?.ivContactAvatar?.setImageResource(R.drawable.ic_sim2)
//        }
    }

    override fun getItemCount(): Int = contactList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContactViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(itemView)
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGroupName: TextView = itemView.findViewById(R.id.tvGroupName)
        val tvContactName: TextView = itemView.findViewById(R.id.tvContactName)
        val tvPhoneNumber: TextView = itemView.findViewById(R.id.tvPhoneNumber)
        val ivContactAvatar: ImageView = itemView.findViewById(R.id.ivContactAvatar)
        val ivCall: ImageView = itemView.findViewById(R.id.ivCall)
        val ivMessage: ImageView = itemView.findViewById(R.id.ivMessage)
        val ivSelectContact: ImageView = itemView.findViewById(R.id.ivSelectContact)
        val lineHorizontal: View = itemView.findViewById(R.id.lineHorizontal)
    }
}