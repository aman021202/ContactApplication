package com.example.contactapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactAdapter(private val listOfContact:List<ContactData>):
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    class ContactViewHolder(view:View):RecyclerView.ViewHolder(view){
        val image:ImageView=view.findViewById(R.id.iv_contact)
        val name:TextView=view.findViewById(R.id.tv_contact_name)
        val phone_number:TextView=view.findViewById(R.id.tv_phone_number)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_contact,parent,false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentcontact=listOfContact[position]
       holder.image.setImageURI(currentcontact.imageres)
 /*adpter variable view holde*/ holder.name.text=currentcontact.name /*data class variable*/
        holder.phone_number.text=currentcontact.phone_number
    }

    override fun getItemCount(): Int {
        return listOfContact.size
    }

}