  package com.burhan.apnabingo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class signin : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        auth = FirebaseAuth.getInstance()

    }

    fun buRegisterClickEvent(view: View) {
        var tvEmail=findViewById<TextView>(R.id.tvEmail)
        var tvPassword=findViewById<TextView>(R.id.tvPassword)
        var tvChangePassword=findViewById<TextView>(R.id.tvChangePassword)

        var email=tvEmail.text.toString().trim()
        var password=tvPassword.text.toString().trim()
        var changePassword=tvChangePassword.text.toString().trim()

        if(email.trim().equals("")){
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show()
            return
        }
        if(password.trim().equals("")){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show()
            return
        }
        if(changePassword.trim().equals("")){
            Toast.makeText(this,"Please Enter Confirm Password",Toast.LENGTH_SHORT).show()
            return
        }
        if(password.trim().length<6){
            Toast.makeText(this,"Too short password.",Toast.LENGTH_SHORT).show()
            return
        }
        if(!password.trim().equals(changePassword.trim())){
            Toast.makeText(this,"Confirm Password not matched.",Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"REGISTER SUCCESSFULLY",Toast.LENGTH_SHORT).show()
                    var intent= Intent(this,login::class.java)
                    startActivity(intent)


                } else {
                    Toast.makeText(this,"AUTHENTICATION FAILED",Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun buLoginClick(view: View) {
        var intent= Intent(this,login::class.java)
        startActivity(intent)
    }
}