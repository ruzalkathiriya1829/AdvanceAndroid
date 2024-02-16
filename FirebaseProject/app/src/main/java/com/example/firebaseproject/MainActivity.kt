package com.example.firebaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var email : EditText
    lateinit var passwd : EditText
    lateinit var btnregister : Button

    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {

        mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email)
        passwd=findViewById(R.id.passwd)
        btnregister=findViewById(R.id.btnregister)

        btnregister.setOnClickListener {



            var emailid=email.text.toString()
            var passwotrdid=passwd.text.toString()


            mAuth.createUserWithEmailAndPassword(emailid, passwotrdid)
                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult?> {
                    override fun onComplete(@NonNull task: Task<AuthResult?>) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            android.util.Log.d("TAG", "createUserWithEmail:success")
                            val user: /*@@wyeuex@@*/FirebaseUser? = mAuth.getCurrentUser()
                            Toast.makeText(this@MainActivity, "account created success", Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            android.util.Log.w("TAG", "createUserWithEmail:failure", task.getException())
                            Toast.makeText(this@MainActivity , "Authentication failed.", Toast.LENGTH_SHORT).show()

                        }
                    }
                })

        }

    }

}
