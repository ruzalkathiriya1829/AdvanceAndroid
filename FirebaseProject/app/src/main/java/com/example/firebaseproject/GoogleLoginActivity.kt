package com.example.firebaseproject

import android.R.attr
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class GoogleLoginActivity : AppCompatActivity() {

    lateinit var bt_sign_in : SignInButton
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var btnLogout : Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)
        initView()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun initView() {
        bt_sign_in=findViewById(R.id.bt_sign_in)
        btnLogout=findViewById(R.id.btnLogout)

        firebaseAuth = FirebaseAuth.getInstance()
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("15302583440-3cck4fr3e1ikusfjcmslj30l76atlefh.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Initialize sign in client

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
             bt_sign_in.setOnClickListener{


            // Initialize sign in intent
            val intent = googleSignInClient.signInIntent
            // Start activity for result
            startActivityForResult(intent, 100)
        }

        btnLogout.setOnClickListener {
            googleSignInClient.signOut()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if(requestCode == 100 && resultCode == RESULT_OK)
        {
            val signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data)

            if(signInAccountTask.isSuccessful)
            {
                val googleSignInAccount = signInAccountTask.getResult(ApiException::class.java)

                if(googleSignInAccount != null)
                {
                    val authCredential: AuthCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
                    firebaseAuth.signInWithCredential(authCredential)
                        .addOnCompleteListener(this) { task ->
                            // Check condition
                            if (task.isSuccessful) {
                                // When task is successful redirect to profile activity
//                                startActivity(Intent(this, ProfileActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                                // Display Toast

                                Toast.makeText(this, "Firebase authentication successful", Toast.LENGTH_SHORT).show()
                            } else {
                                // When task is unsuccessful display Toast
                                Toast.makeText(this, "faillllll", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
    }
}