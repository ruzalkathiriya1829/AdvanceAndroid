package com.example.firebaseprojectdemo.Activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseprojectdemo.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
//            reload()
            Toast.makeText(this@MainActivity, "You have a Already Account.", Toast.LENGTH_SHORT).show()
            intent = Intent(this@MainActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
//        else
//        {
//            initView()
//        }
    }

    private fun initView() {

        auth = FirebaseAuth.getInstance()


        binding.btnRegister.setOnClickListener {

            var email = binding.edtEmail.text.toString()
            var password = binding.edtPassword.text.toString()

            if(email.isEmpty())
            {
                Toast.makeText(this@MainActivity, "Email is required", Toast.LENGTH_SHORT).show()
            }
            else if(password.isEmpty())
            {
                Toast.makeText(this@MainActivity, "Password is required", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, object : OnCompleteListener<AuthResult?>{

                    override fun onComplete(task: Task<AuthResult?>) {

                        if(task.isSuccessful())
                        {
                            android.util.Log.d("TAG","createUserWithEmail:success")
                            val user: /*@@wyeuex@@*/FirebaseUser? = auth.getCurrentUser()
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

    }