package com.example.firebaseprojectdemo.PushNotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.example.firebaseprojectdemo.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

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