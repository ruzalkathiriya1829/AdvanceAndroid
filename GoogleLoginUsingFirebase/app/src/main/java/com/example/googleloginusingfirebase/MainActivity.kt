package com.example.googleloginusingfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.googleloginusingfirebase.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {

        firebaseAuth = FirebaseAuth.getInstance()

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("283822910574-so2f0btbklvrifhstfd1qpd7pupjv0k3.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        binding.btSignIn.setOnClickListener {


            // Initialize sign in intent
            val intent = googleSignInClient.signInIntent

            // Start activity for result
            startActivityForResult(intent, 100)

        }

        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        // Check condition
        if (firebaseUser != null) {
            // When user already sign in redirect to profile activity
            startActivity(
                Intent(this@MainActivity, ProfileActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if(requestCode == 100 && resultCode == RESULT_OK)
        {
            val signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data)

            if(signInAccountTask.isSuccessful)
            {
                val s = "Google sign in successful"
                // Display Toast
                displayToast(s)

                val googleSignInAccount = signInAccountTask.getResult(ApiException::class.java)

                if(googleSignInAccount != null)
                {
                    val authCredential: AuthCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
                    firebaseAuth.signInWithCredential(authCredential)
                        .addOnCompleteListener(this) { task ->
                            // Check condition

                            if (task.isSuccessful) {
                                // When task is successful redirect to profile activity
                                startActivity(Intent(this, ProfileActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                                // Display Toast

                                displayToast("Firebase authentication successful")
                            } else {
                                // When task is unsuccessful display Toast
                                displayToast(
                                    "Authentication Failed :" + task.exception?.message
                                )
                            }
                        }
                }
            }
        }
    }

    private fun displayToast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
    }
}