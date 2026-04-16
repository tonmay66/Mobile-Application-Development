package com.example.contactbookapp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContactAdapter
    private val contacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val searchView = findViewById<SearchView>(R.id.searchView)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val emptyView = findViewById<TextView>(R.id.emptyView)

        adapter = ContactAdapter(this, contacts)
        listView.adapter = adapter
        listView.emptyView = emptyView

        // Initial Data
        contacts.add(Contact("John Doe", "1234567890", "john@example.com"))
        contacts.add(Contact("Jane Smith", "0987654321", "jane@example.com"))
        adapter.notifyDataSetChanged()

        fab.setOnClickListener {
            showAddContactDialog()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val contact = adapter.getItem(position)
            contact?.let {
                Toast.makeText(this, "Name: ${it.name}\nPhone: ${it.phone}\nEmail: ${it.email}", Toast.LENGTH_LONG).show()
            }
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val contact = adapter.getItem(position)
            contact?.let { showDeleteConfirmationDialog(it) }
            true
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun showAddContactDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add New Contact")

        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_contact, null)
        val etName = view.findViewById<EditText>(R.id.etName)
        val etPhone = view.findViewById<EditText>(R.id.etPhone)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)

        builder.setView(view)
        builder.setPositiveButton("Add") { _, _ ->
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val newContact = Contact(name, phone, email)
                contacts.add(newContact)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Please enter Name and Phone", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    private fun showDeleteConfirmationDialog(contact: Contact) {
        AlertDialog.Builder(this)
            .setTitle("Delete Contact")
            .setMessage("Are you sure you want to delete ${contact.name}?")
            .setPositiveButton("Delete") { _, _ ->
                contacts.remove(contact)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
