package com.example.firebaseapp.screen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.firebaseapp.R
import com.example.firebaseapp.service.FirebaseFirestoreManager
import com.example.firebaseapp.service.FirebaseStorageManager

class NewHistoryScreen : AppCompatActivity() {

    private lateinit var yertext: EditText
    private lateinit var title: EditText
    private lateinit var notes: EditText
    private lateinit var image: ImageView
    private lateinit var ekle: Button
    lateinit var imageData : Uri

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_history_screen)

        val mesaj = intent.getStringExtra("emailpath")

        yertext = findViewById(R.id.yertext)
        title = findViewById(R.id.titletext)
        notes = findViewById(R.id.notemultitext)
        image = findViewById(R.id.resimekleview)
        ekle = findViewById(R.id.eklebtn)

        image.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        ekle.setOnClickListener {
            val baslik = title.text.toString()
            val yer = yertext.text.toString()
            val notlar = notes.text.toString()
            val dataManager = FirebaseFirestoreManager()
            val storageManager = FirebaseStorageManager()

            if (mesaj != null) {
                storageManager.uploadImageByEmail(mesaj, imageData,
                    onSuccess = { imageUrl ->
                        // İşlem başarılı olduğunda yapılacaklar
                        println("Resim başarıyla yüklendi. URL: $imageUrl")
                    },
                    onFailure = {
                        // İşlem başarısız olduğunda yapılacaklar
                        println("Resim yüklenirken hata oluştu.")
                    }
                )
            }

            if (mesaj != null) {
                dataManager.saveUserData(mesaj,baslik,yer,notlar){deger ->
                    if (deger == false){
                        Toast.makeText(this,"Error saving",Toast.LENGTH_LONG).show()
                    }
                    else{
                        val intent = Intent(this, HomeScreen::class.java)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                    
            }


        }
    }


}

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            image.setImageURI(selectedImageUri)
            if (selectedImageUri != null) {
                imageData = selectedImageUri
            }
        }
    }



}
