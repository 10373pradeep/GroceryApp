package com.pkscoding.gapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GRVAdapter.GroceryItemClickInterface {
    private lateinit var itemsRv: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<GItems>
    lateinit var grvAdapter: GRVAdapter
    lateinit var gViewModel: GViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRv = findViewById(R.id.idRVItems)
        addFAB = findViewById(R.id.idFABAdd)
        list = ArrayList<GItems>()
        grvAdapter = GRVAdapter(list, this)
        itemsRv.layoutManager = LinearLayoutManager(this)
        itemsRv.adapter = grvAdapter
        val gRepository = GRepository(GDataBase.invoke(this))
        val factory = GViewModelFactory(gRepository)
        gViewModel = ViewModelProvider(this, factory).get(GViewModel::class.java)
        gViewModel.getAllGroceryItems().observe(this, Observer {
            grvAdapter.list = it
            grvAdapter.notifyDataSetChanged()

        })

        addFAB.setOnClickListener {
            openDialog()

        }

    }

    fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.g_add_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val add = dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdt = dialog.findViewById<EditText>(R.id.idEditItemName)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.idEditItemPrice)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.idEditItemQuantity)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        add.setOnClickListener {
            val itemName : String = itemEdt.text.toString()
            val itemPrice : String = itemPriceEdt.text.toString()
            val itemQuantity : String = itemQuantityEdt.text.toString()
            val qty : Int = itemQuantity.toInt()
            val pr: Int = itemPrice.toInt()
            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()){
                val items = GItems(itemName,qty,pr)
                gViewModel.insert(items)
                Toast.makeText(applicationContext, "Item Inserted..",Toast.LENGTH_SHORT).show()
                grvAdapter.notifyDataSetChanged()
                dialog.dismiss()

            }else{
                Toast.makeText(applicationContext, "Item Inserted..",Toast.LENGTH_SHORT).show()

            }
        }
        dialog.show()

    }

    override fun onItemClick(gItems: GItems) {
        gViewModel.delete(gItems)
        grvAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Please enter all the data..",Toast.LENGTH_SHORT).show()

    }
}