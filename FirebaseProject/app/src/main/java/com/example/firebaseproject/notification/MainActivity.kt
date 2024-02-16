package com.example.firebaseproject.notification


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseproject.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)




        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(object : OnCompleteListener<String?> {
                override fun onComplete(@NonNull task: Task<String?>) {
                    if (!task.isSuccessful()) {
                        Log.w("TAG", "Fetching FCM registration token failed", task.getException())
                        return
                    }

                    // Get new FCM registration token
                    val token: String = task.getResult() ?: ""
                    Log.e("TAG", "onComplete: ==  "+token )
                    // Log and toast
//                    val msg = getString(com.example.firebaseproject.R.string.msg_token_fmt, token)
//                    Log.d("TAG", msg)
//                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                }
            })
    }
}