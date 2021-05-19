package com.burhan.apnabingo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.burhan.apnabingo.R.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance()
    var myRef=database.reference
    var myEmail:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        var b:Bundle= intent.extras!!
        myEmail= b.getString("email")

        inComingCalls()
        var bingoLayout=findViewById<TableLayout>(id.bingoLayout)
        bingoLayout.isVisible=false
    }

    var activePlayer=1
    var player1= mutableSetOf<Int>()

    fun buStartClickEvent(view: View) {
        val buStart: Button =view as Button
        buStart.isVisible=false
        var bingoLayout=findViewById<TableLayout>(id.bingoLayout)
        bingoLayout.isVisible=true

        var shuffledList = ArrayList((1..25).toList())
        shuffledList.shuffle()
        var buSelected: Button=findViewById(id.button1)
        for(i in 1..25){
            when(i){
                1->buSelected=findViewById(id.button1)
                2->buSelected=findViewById(id.button2)
                3->buSelected=findViewById(id.button3)
                4->buSelected=findViewById(id.button4)
                5->buSelected=findViewById(id.button5)
                6->buSelected=findViewById(id.button6)
                7->buSelected=findViewById(id.button7)
                8->buSelected=findViewById(id.button8)
                9->buSelected=findViewById(id.button9)
                10->buSelected=findViewById(id.button10)
                11->buSelected=findViewById(id.button11)
                12->buSelected=findViewById(id.button12)
                13->buSelected=findViewById(id.button13)
                14->buSelected=findViewById(id.button14)
                15->buSelected=findViewById(id.button15)
                16->buSelected=findViewById(id.button16)
                17->buSelected=findViewById(id.button17)
                18->buSelected=findViewById(id.button18)
                19->buSelected=findViewById(id.button19)
                20->buSelected=findViewById(id.button20)
                21->buSelected=findViewById(id.button21)
                22->buSelected=findViewById(id.button22)
                23->buSelected=findViewById(id.button23)
                24->buSelected=findViewById(id.button24)
                25->buSelected=findViewById(id.button25)

            }
            buSelected.text=shuffledList[i-1].toString()
        }
    }
    fun reset() {
        var buSelected: Button = findViewById(id.button1)
        for (i in 1..25) {
            when (i) {
                1 -> buSelected = findViewById(id.button1)
                2 -> buSelected = findViewById(id.button2)
                3 -> buSelected = findViewById(id.button3)
                4 -> buSelected = findViewById(id.button4)
                5 -> buSelected = findViewById(id.button5)
                6 -> buSelected = findViewById(id.button6)
                7 -> buSelected = findViewById(id.button7)
                8 -> buSelected = findViewById(id.button8)
                9 -> buSelected = findViewById(id.button9)
                10 -> buSelected = findViewById(id.button10)
                11 -> buSelected = findViewById(id.button11)
                12 -> buSelected = findViewById(id.button12)
                13 -> buSelected = findViewById(id.button13)
                14 -> buSelected = findViewById(id.button14)
                15 -> buSelected = findViewById(id.button15)
                16 -> buSelected = findViewById(id.button16)
                17 -> buSelected = findViewById(id.button17)
                18 -> buSelected = findViewById(id.button18)
                19 -> buSelected = findViewById(id.button19)
                20 -> buSelected = findViewById(id.button20)
                21 -> buSelected = findViewById(id.button21)
                22 -> buSelected = findViewById(id.button22)
                23 -> buSelected = findViewById(id.button23)
                24 -> buSelected = findViewById(id.button24)
                25 -> buSelected = findViewById(id.button25)

            }
            buSelected.text = ""
            buSelected.setBackgroundColor(buSelected.resources.getColor(R.color.buttonBackground))
            buSelected.setTextColor(buSelected.resources.getColor(R.color.black))
            buSelected.isEnabled = true
            player1.clear()

        }
        for(i in 1..5){
            when(i){
                1 -> buSelected = findViewById(id.buttonB)
                2 -> buSelected = findViewById(id.buttonI)
                3 -> buSelected = findViewById(id.buttonN)
                4 -> buSelected = findViewById(id.buttonG)
                5 -> buSelected = findViewById(id.buttonO)
            }
            buSelected.setBackgroundResource(color.yellow)
            buSelected.isEnabled = true
        }
        var buStart = findViewById<Button>(id.buStart)
        buStart.isVisible = true
        var bingoLayout=findViewById<TableLayout>(id.bingoLayout)
        bingoLayout.isVisible=false
    }
    fun inComingCalls(){
        myRef.child("user").child(splitString(myEmail.toString())).child("Request").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    val dt=snapshot.value as HashMap<String,Any>
                    if(dt!=null){
                        var value:String
                        for(key in dt.keys){
                            value=dt[key].toString()
                            var email=findViewById<TextView>(id.etEmail)
                            email.text = value
                            myRef.child("user").child(splitString(myEmail.toString())).child("Request").setValue(true)
                            break
                        }
                    }
                }catch(ex:Exception){

                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }



    fun buClick(view: View) {
        val buSelected: Button =view as Button

        var cellId=0
        when(buSelected.id){
            id.button1->cellId=1
            id.button2->cellId=2
            id.button3->cellId=3
            id.button4->cellId=4
            id.button5->cellId=5
            id.button6->cellId=6
            id.button7->cellId=7
            id.button8->cellId=8
            id.button9->cellId=9
            id.button10->cellId=10
            id.button11->cellId=11
            id.button12->cellId=12
            id.button13->cellId=13
            id.button14->cellId=14
            id.button15->cellId=15
            id.button16->cellId=16
            id.button17->cellId=17
            id.button18->cellId=18
            id.button19->cellId=19
            id.button20->cellId=20
            id.button21->cellId=21
            id.button22->cellId=22
            id.button23->cellId=23
            id.button24->cellId=24
            id.button25->cellId=25

        }
        Log.d("buClick: cellId",cellId.toString())
        buSelected.setBackgroundColor(buSelected.resources.getColor(R.color.black))
        buSelected.setTextColor(buSelected.resources.getColor(R.color.red))
        buSelected.isEnabled=false
        player1.add(cellId)
        Log.i("info message","Size of array ${player1.size}")
        for(ele in player1){
            Log.i("info message",ele.toString())
        }
        myRef.child("playerOnline").child(sessionID.toString()).child(buSelected.text.toString()).setValue(myEmail)
//        myRef.child("playerOnline").child(sessionID.toString()).child("status"+myEmail).setValue("no")

    }

    fun checkwin(){

        var count1=0
        //row 1
        if(player1.contains(1)&&player1.contains(2)&&player1.contains(3)&&player1.contains(4)&&player1.contains(5)){
            count1+=1
        }

        //row 2
        if(player1.contains(6)&&player1.contains(7)&&player1.contains(8)&&player1.contains(9)&&player1.contains(10)){
            count1+=1
        }

        //row 3
        if(player1.contains(11)&&player1.contains(12)&&player1.contains(13)&&player1.contains(14)&&player1.contains(15)){
            count1+=1
        }

        //row 4
        if(player1.contains(16)&&player1.contains(17)&&player1.contains(18)&&player1.contains(19)&&player1.contains(20)){
            count1+=1
        }

        //row 5
        if(player1.contains(21)&&player1.contains(22)&&player1.contains(23)&&player1.contains(24)&&player1.contains(25)){
            count1+=1
        }

        //col 1
        if(player1.contains(1)&&player1.contains(6)&&player1.contains(11)&&player1.contains(16)&&player1.contains(21)){
            count1+=1
        }

        //col 2
        if(player1.contains(2)&&player1.contains(7)&&player1.contains(12)&&player1.contains(17)&&player1.contains(22)){
            count1+=1
        }

        //col 3
        if(player1.contains(3)&&player1.contains(8)&&player1.contains(13)&&player1.contains(18)&&player1.contains(23)){
            count1+=1
        }

        //col 4
        if(player1.contains(4)&&player1.contains(9)&&player1.contains(14)&&player1.contains(19)&&player1.contains(24)){
            count1+=1
        }

        //col 5
        if(player1.contains(5)&&player1.contains(10)&&player1.contains(15)&&player1.contains(20)&&player1.contains(25)){
            count1+=1
        }

        // dig 1
        if(player1.contains(1)&&player1.contains(7)&&player1.contains(13)&&player1.contains(19)&&player1.contains(25)){
            count1+=1
        }

        // dig 2
        if(player1.contains(5)&&player1.contains(9)&&player1.contains(13)&&player1.contains(17)&&player1.contains(21)){
            count1+=1
        }

        if(count1>=5){
            myRef.child("playerOnline").child(sessionID.toString()).child("status_$myEmail").setValue("yes")
        }
        else{
            myRef.child("playerOnline").child(sessionID.toString()).child("status_$myEmail").setValue("yes")
        }

    }



    fun buAcceptEvent(view: android.view.View) {
        var userEmail:String?=null
        var tvEmail=findViewById<TextView?>(id.etEmail)
        userEmail=tvEmail!!.text.toString()
        myRef.child("user").child(splitString(userEmail!!)).child("Request").push().setValue(myEmail!!)
        playOnline(splitString(userEmail)+splitString(myEmail!!))
        playerSymbol=2
    }

    fun RequestEvent(view: View) {
        var userEmail:String?=null
        var tvEmail=findViewById<TextView?>(id.etEmail)
        if(tvEmail!=null){
            userEmail=tvEmail.text.toString().trim()
        }
        Toast.makeText(this,"OKKK HERE",Toast.LENGTH_SHORT).show()
        myRef.child("user").child(splitString(userEmail!!)).child("Request").push().setValue(myEmail!!)
        playOnline(splitString(myEmail!!)+splitString(userEmail))
        playerSymbol=1
    }

    fun splitString(str:String):String{

        var split = str.split("@")
        return split[0]
    }

    var sessionID:String?=null
    var playerSymbol:Int?=null

    fun playOnline(sessionID:String){
        this.sessionID=sessionID
        myRef.child("playerOnline").removeValue()
        myRef.child("playerOnline").child(sessionID).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    val dt=snapshot.value as HashMap<String,Any>
                    if(dt!=null){
                        var value:String
                        for(key in dt.keys){
                            value=dt[key].toString()
                            if(value==myEmail){
                                continue
                            }
                            else{
                                autoPlay(key.toInt())
                            }

                        }
                    }
                }catch(ex:Exception){
                    Log.e("error message","Event Listener",ex)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("error message",error.toString())
            }
        })
    }

    fun autoPlay(number:Int){
        var buSelected: Button = findViewById(id.button1)
        for (i in 1..25) {
            when (i) {
                1 -> buSelected = findViewById(id.button1)
                2 -> buSelected = findViewById(id.button2)
                3 -> buSelected = findViewById(id.button3)
                4 -> buSelected = findViewById(id.button4)
                5 -> buSelected = findViewById(id.button5)
                6 -> buSelected = findViewById(id.button6)
                7 -> buSelected = findViewById(id.button7)
                8 -> buSelected = findViewById(id.button8)
                9 -> buSelected = findViewById(id.button9)
                10 -> buSelected = findViewById(id.button10)
                11 -> buSelected = findViewById(id.button11)
                12 -> buSelected = findViewById(id.button12)
                13 -> buSelected = findViewById(id.button13)
                14 -> buSelected = findViewById(id.button14)
                15 -> buSelected = findViewById(id.button15)
                16 -> buSelected = findViewById(id.button16)
                17 -> buSelected = findViewById(id.button17)
                18 -> buSelected = findViewById(id.button18)
                19 -> buSelected = findViewById(id.button19)
                20 -> buSelected = findViewById(id.button20)
                21 -> buSelected = findViewById(id.button21)
                22 -> buSelected = findViewById(id.button22)
                23 -> buSelected = findViewById(id.button23)
                24 -> buSelected = findViewById(id.button24)
                25 -> buSelected = findViewById(id.button25)

            }
            if (buSelected.text.equals(number.toString())) {
                buSelected.setBackgroundColor(buSelected.resources.getColor(R.color.black))
                buSelected.setTextColor(buSelected.resources.getColor(R.color.white))
                buSelected.isEnabled = false
                player1.add(i)
                break
            }
        }
    }

}