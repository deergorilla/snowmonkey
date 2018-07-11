package com.snowmonkey.sample.firebase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth




class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    private val email by lazy { findViewById<EditText>(R.id.email_text) }
    private val password by lazy { findViewById<EditText>(R.id.password_text) }
    private val signin by lazy { findViewById<TextView>(R.id.signin_text) }
    private val signup by lazy { findViewById<TextView>(R.id.signup_text) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebaseAuth()
        initClickListener()
    }
    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }
    override fun onStop() {
        super.onStop()
        mAuth.removeAuthStateListener(mAuthListener)
    }
    fun initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user!=null) {
                // User is signed in
                Log.d("aaaa", "onAuthStateChanged:signed_in:" + user.uid)
            } else {
                // User is signed out
                Log.d("aaaa", "onAuthStateChanged:signed_out")
            }
            // ...
        }
    }
    fun initClickListener() {
        signin.setOnClickListener {
        }

        signup.setOnClickListener {
            mAuth.signInWithEmailAndPassword(email.text.toString(),password.text.toString())
        }
    }

}
