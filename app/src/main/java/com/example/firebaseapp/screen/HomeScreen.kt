package com.example.firebaseapp.screen

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.firebaseapp.R
import com.example.firebaseapp.adapter.HistoryAdapter
import com.example.firebaseapp.models.History
import com.example.firebaseapp.service.FirebaseFirestoreManager
import com.example.firebaseapp.service.FirebaseStorageManager

class HomeScreen : AppCompatActivity() {

    lateinit var yeniani: Button
    lateinit var gezilistview: ListView
    lateinit var imageurl: String
    lateinit var historyList: MutableList<History>
    lateinit var adapter: HistoryAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)



        val mesaj = intent.getStringExtra("email")
        val emailPath = mesaj?.replace(".", "-")

        val dataManager = FirebaseFirestoreManager()
        historyList = mutableListOf<History>()
        adapter = HistoryAdapter(this, R.layout.custom_list_item, historyList)
        val storageManager = FirebaseStorageManager()

        if (emailPath != null) {
            storageManager.getImageUrlByEmail(emailPath,
                onSuccess = { imageUrl ->
                    imageurl = imageUrl
                    dataManager.getUserData(email = emailPath) { title, city, notes ->

                        val title = title
                        val city = city
                        val notes = notes

                        if (title != null && city != null && notes != null) {
                            val historyItem = History(title, city, notes, imageurl)
                            historyList.add(historyItem)
                            Log.d("enes",historyList.toString())
                            adapter.notifyDataSetChanged()
                        }
                    }
                },
                onFailure = {
                    println("Resim URL'sini alırken hata oluştu.")
                }
            )
        }

// ...



    yeniani = findViewById(R.id.yenianıbtn)
    gezilistview = findViewById(R.id.gezilistview)

        gezilistview.adapter = adapter
        adapter.notifyDataSetChanged()

        yeniani.setOnClickListener {
            intent  = Intent(this, NewHistoryScreen::class.java)
            intent.putExtra("emailpath",emailPath)
            startActivity(intent)
            finish()
        }


    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()

    }



}