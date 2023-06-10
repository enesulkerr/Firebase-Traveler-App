package com.example.firebaseapp.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerUser(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Kullanıcı kaydedildi.")
                } else {
                    val errorMessage = task.exception?.message ?: "Bir hata oluştu."
                    callback(false, errorMessage)
                }
            }
    }

    fun loginUser(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Giriş başarılı.")
                } else {
                    val errorMessage = task.exception?.message ?: "Bir hata oluştu."
                    callback(false, errorMessage)
                }
            }
    }

    fun logoutUser() {
        auth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}
