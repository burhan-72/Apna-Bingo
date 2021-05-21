package com.burhan.apnabingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class login : AppCompatActivity() {


    var auth: FirebaseAuth?=null
    var database = FirebaseDatabase.getInstance()
    var myRef=database.reference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()



    }

    fun buLoginClick(view: View) {
        var tvEmail=findViewById<TextView>(R.id.tvEmail)
        var tvPassword=findViewById<TextView>(R.id.tvPassword)
        var buLogin=findViewById<Button>(R.id.buLogin)

        var email=tvEmail.text.toString().trim()
        var password=tvPassword.text.toString().trim()

        if(email.equals("")){
            Toast.makeText(this,"Please Enter Email", Toast.LENGTH_SHORT).show()
            return
        }
        if(password.equals("")){
            Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show()
            return
        }

        auth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"LOGIN SUCCESSFULLY",Toast.LENGTH_SHORT).show()
                    var currentUser=auth!!.currentUser
                    if (currentUser != null) {
                        var userName=splitString(currentUser.email.toString())
                        myRef.child("user").child(userName).child("Request").setValue(currentUser.uid)
                    }
                    loadMain()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Incorrect email or password",Toast.LENGTH_SHORT).show()

                }
            }
    }
    fun buRegisterClick(view: View) {
        var buRegister=findViewById<Button>(R.id.buRegister)
        var intent= Intent(this,signin::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        loadMain()
    }

    fun loadMain(){
        var currentUser=auth!!.currentUser
        if(currentUser!=null) {

            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", currentUser.email)
            intent.putExtra("uid", currentUser.uid)
            startActivity(intent)
            finish()
        }
    }

    fun splitString(str:String):String{

        var split = str.split("@")
        return split[0]
    }
}