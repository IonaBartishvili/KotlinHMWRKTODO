package com.example.todo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var array = arrayListOf<Any>(
        "Melbourne",
        "Cape Town",
        "Barcelona",
        "London"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ArrayAdapter(this, R.layout.row_ui, array)
        val listView: ListView = findViewById(R.id.listview_1)
        listView.setAdapter(adapter)

        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                //value of item that is clicked
                val itemValue = listView.getItemAtPosition(position)

                // Toast the values
                Toast.makeText(
                    applicationContext, "Position : $position" +
                            "\nItem Value: $itemValue", Toast.LENGTH_LONG
                ).show()

            }
        }

        addBtn.setOnClickListener {
            val newTodo = todoInput.text.toString()
            if (!TextUtils.isEmpty(newTodo)) {
                adapter.add(newTodo.capitalize())
                adapter.setNotifyOnChange(true)

                todoInput.setText("")

                Toast.makeText(this, array.joinToString(), Toast.LENGTH_LONG).show()
            }
        }

        save.setOnClickListener{
            saveInfo()
        }

        load.setOnClickListener{
            loadInfo()
        }

    }

    private fun saveInfo(){
        val pref = getSharedPreferences("TODOS", Context.MODE_PRIVATE).edit()
        pref.putString("TODOs", array.joinToString()).apply()
    }

    private fun loadInfo(){

        val p = getSharedPreferences("TODOS", Context.MODE_PRIVATE)

        val info = p.getString("TODOs", null)

        Toast.makeText(this, info, Toast.LENGTH_LONG).show()
    }

}

