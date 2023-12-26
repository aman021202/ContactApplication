package com.example.contactapplication

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rv:RecyclerView
    private lateinit var fab:FloatingActionButton
    private lateinit var name:EditText
    private lateinit var phone_number:EditText
    private lateinit var previewimage:ImageView
    private lateinit var btnChooseImage:Button
    private lateinit var btnAddContact:Button
    private lateinit var contactadapter:ContactAdapter
    val listOfContact= mutableListOf<ContactData>()
    private lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv=findViewById(R.id.rv)
        rv.layoutManager=LinearLayoutManager(this)
        contactadapter= ContactAdapter(listOfContact)
        rv.adapter=contactadapter


         fab=findViewById(R.id.fab)
        fab.setOnClickListener {
            showDialog()
        }
    }
    fun showDialog(){
         dialog=Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_dialog)
        name=dialog.findViewById(R.id.et_name)
        phone_number=dialog.findViewById(R.id.et_phone_number)
        btnChooseImage=dialog.findViewById(R.id.btn_image)
        btnAddContact=dialog.findViewById(R.id.btn_add_contact)
        previewimage=dialog.findViewById(R.id.iv_preview)
        btnChooseImage.setOnClickListener{
            val galleryIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent,101)
        }
//
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101&&resultCode== RESULT_OK){
            previewimage.visibility= View.VISIBLE
            previewimage.setImageURI(data?.data)
            btnAddContact.setOnClickListener {
                val namefromedt=name.text.toString()
                val phone_numberfromedt=phone_number.text.toString()
                val imageed=data?.data
                val contact=ContactData(
                    imageres=imageed!!,
                    name=namefromedt,
                    phone_number = phone_numberfromedt
                )
                listOfContact.add(contact)
                contactadapter.notifyDataSetChanged()
                dialog.dismiss()
            }

        }
    }
}