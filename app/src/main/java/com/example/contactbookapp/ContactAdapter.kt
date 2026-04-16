package com.example.contactbookapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import java.util.*

class ContactAdapter(context: Context, private var contacts: MutableList<Contact>) :
    ArrayAdapter<Contact>(context, R.layout.item_contact, contacts) {

    private var filteredContacts: MutableList<Contact> = contacts

    override fun getCount(): Int = filteredContacts.size
    override fun getItem(position: Int): Contact? = filteredContacts[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
            viewHolder = ViewHolder()
            viewHolder.tvAvatar = view.findViewById(R.id.tvAvatar)
            viewHolder.tvName = view.findViewById(R.id.tvName)
            viewHolder.tvPhone = view.findViewById(R.id.tvPhone)
            viewHolder.ivCall = view.findViewById(R.id.ivCall)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val contact = getItem(position)
        contact?.let {
            viewHolder.tvName?.text = it.name
            viewHolder.tvPhone?.text = it.phone
            viewHolder.tvAvatar?.text = it.initial
            // Color is set dynamically, background is set in XML
            viewHolder.tvAvatar?.background?.setTint(getColorForInitial(it.initial))

            viewHolder.ivCall?.setOnClickListener {
                // Implementation for call could go here
            }
        }

        return view
    }

    private fun getColorForInitial(initial: String): Int {
        val colors = listOf("#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#2196F3", "#03A9F4", "#00BCD4", "#009688", "#4CAF50", "#8BC34A", "#CDDC39", "#FFEB3B", "#FFC107", "#FF9800", "#FF5722")
        val index = if (initial.isNotEmpty()) initial[0].code % colors.size else 0
        return Color.parseColor(colors[index])
    }

    private class ViewHolder {
        var tvAvatar: TextView? = null
        var tvName: TextView? = null
        var tvPhone: TextView? = null
        var ivCall: View? = null
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint?.toString()?.lowercase(Locale.getDefault())
                val results = FilterResults()
                
                if (queryString.isNullOrEmpty()) {
                    results.values = contacts
                } else {
                    val filteredList = contacts.filter {
                        it.name.lowercase(Locale.getDefault()).contains(queryString)
                    }
                    results.values = filteredList
                }
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredContacts = results?.values as MutableList<Contact>
                notifyDataSetChanged()
            }
        }
    }
}
