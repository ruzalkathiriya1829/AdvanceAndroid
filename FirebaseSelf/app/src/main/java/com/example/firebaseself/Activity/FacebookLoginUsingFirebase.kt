package com.example.firebaseself.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseself.databinding.ActivityFacebookLoginUsingFirebaseBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.Firebase
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import java.util.Arrays


class FacebookLoginUsingFirebase : AppCompatActivity() {

    lateinit var binding: ActivityFacebookLoginUsingFirebaseBinding
    lateinit var callbackManager: CallbackManager
    private lateinit var auth: FirebaseAuth

    private val EMAIL = "email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacebookLoginUsingFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {


        binding.btnNext.setOnClickListener {

            intent = Intent(this@FacebookLoginUsingFirebase, RecyclerviewWithFirebase::class.java)
            startActivity(intent)
        }


        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create();

        binding.btnFBLogin.setReadPermissions(Arrays.asList(EMAIL,"public_profile"));

        // Callback registration
        // Callback registration
        binding.btnFBLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // App code
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("TAG", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this, "com/example/firebaseself_admin/MainActivity.kt"::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

                    Log.d("TAG", "signInWithCredential:success")
                    val user = auth.currentUser
                    Toast.makeText(this, "success login", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

}