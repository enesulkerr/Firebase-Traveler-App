package com.example.firebaseapp.service
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFirestoreManager {

    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun saveUserData(email: String, title: String, city: String, notes: String,callback: (Boolean) -> Unit) {
        val userRef = firestore.collection("users").document(email)
        val userData = hashMapOf(
            "Başlık" to title,
            "Şehir" to city,
            "Notlar" to notes
        )

        userRef.set(userData)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { error ->
                callback(false)

            }
    }

    fun getUserData(email: String, callback: (title: String?, city: String?, notes: String?) -> Unit) {
        val userRef = firestore.collection("users").document(email)

        userRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val userData = document.data as? Map<String, String>
                    val title = userData?.get("Başlık")
                    val city = userData?.get("Şehir")
                    val notes = userData?.get("Notlar")
                    callback(title, city, notes)
                } else {
                    // Belirtilen kullanıcı bulunamadı
                    callback(null, null, null)
                }
            }
            .addOnFailureListener { error ->
                // Veri alımı işlemi başarısız oldu
                callback(null, null, null)
            }
    }

    fun updateUserData(email: String, title: String, city: String, notes: String) {
        val userRef = firestore.collection("users").document(email)
        val userData = hashMapOf(
            "Başlık" to title,
            "Şehir" to city,
            "Notlar" to notes
        )

        userRef.update(userData as Map<String, Any>)
            .addOnSuccessListener {
                // Güncelleme işlemi başarılı olduğunda yapılacak işlemler
            }
            .addOnFailureListener { error ->
                // Güncelleme işlemi başarısız olduğunda yapılacak işlemler
            }
    }

    fun deleteUserData(email: String) {
        val userRef = firestore.collection("users").document(email)

        userRef.delete()
            .addOnSuccessListener {
                // Silme işlemi başarılı olduğunda yapılacak işlemler
            }
            .addOnFailureListener { error ->
                // Silme işlemi başarısız olduğunda yapılacak işlemler
            }
    }
}
