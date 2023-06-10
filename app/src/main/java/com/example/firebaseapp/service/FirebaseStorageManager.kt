package com.example.firebaseapp.service

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseStorageManager {

    private val storage: FirebaseStorage = FirebaseStorage.getInstance()

    fun uploadImageByEmail(email: String, imageUri: Uri, onSuccess: (imageUrl: String) -> Unit, onFailure: () -> Unit) {
        val storageRef: StorageReference = storage.reference
        val imageRef: StorageReference = storageRef.child("images/$email")

        val uploadTask = imageRef.putFile(imageUri)
        Log.d("Uploading image",uploadTask.toString())
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                onSuccess(downloadUri.toString())
            } else {
                onFailure()
            }
        }
    }

    fun getImageUrlByEmail(email: String, onSuccess: (imageUrl: String) -> Unit, onFailure: () -> Unit) {
        val storageRef: StorageReference = storage.reference
        val imageRef: StorageReference = storageRef.child("images/$email")

        imageRef.downloadUrl.addOnSuccessListener { uri ->
            onSuccess(uri.toString())
        }.addOnFailureListener {
            onFailure()
        }
    }
}
