package com.burhan.apnabingo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
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

        var endLayout=findViewById<LinearLayout>(R.id.endLayout)
        endLayout.isVisible=false
    }

    var player1= mutableSetOf<Int>()

    fun StartClickEvent() {
        var bingoLayout=findViewById<TableLayout>(id.bingoLayout)
        bingoLayout.isVisible=true

        var endLayout=findViewById<LinearLayout>(R.id.endLayout)
        endLayout.isVisible=true

        var connLayout=findViewById<LinearLayout>(R.id.connectionLayout)
        connLayout.isVisible=false

        var turnText=findViewById<TextView>(R.id.tvTurn)


        if(playerSymbol==1){
            turnText.text="YOURS TURN"
        }
        else{
            turnText.text="OPPONENTS TURN"
        }

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
                            if(playerSymbol!=null){
                                if(playerSymbol==1){
                                    Toast.makeText(this@MainActivity,"Request Accepted",Toast.LENGTH_SHORT).show()
                                    StartClickEvent()
                                }
                            }
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

        checkwin()

        var turnText=findViewById<TextView>(R.id.tvTurn)
        turnText.text="OPPONENTS TURN"

        myRef.child("playerOnline").child(sessionID.toString()).child(buSelected.text.toString()).setValue(myEmail)
        checkWin2()

    }
    var count1=0
    fun checkwin(){

        count1=0
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

        var buB=findViewById<Button>(R.id.buttonB)
        var buI=findViewById<Button>(R.id.buttonI)
        var buN=findViewById<Button>(R.id.buttonN)
        var buG=findViewById<Button>(R.id.buttonG)
        var buO=findViewById<Button>(R.id.buttonO)

        if(count1>=1){
            buB.setTextColor(buB.resources.getColor(R.color.orange))
        }
        if(count1>=2){
            buI.setTextColor(buB.resources.getColor(R.color.orange))
        }
        if(count1>=3){
            buN.setTextColor(buB.resources.getColor(R.color.orange))
        }
        if(count1>=4){
            buG.setTextColor(buB.resources.getColor(R.color.orange))
        }
        if(count1>=5){
            buO.setTextColor(buB.resources.getColor(R.color.orange))
            myRef.child("playerOnline").child(sessionID.toString()).child(splitString(myEmail!!)).setValue("yes")
        }
    }


    fun buAcceptEvent(view: android.view.View) {
        playerSymbol=2
        var acButton=view as Button
        var userEmail:String?=null
        var tvEmail=findViewById<TextView?>(id.etEmail)
        userEmail=tvEmail!!.text.toString()
        myRef.child("user").child(splitString(userEmail!!)).child("Request").push().setValue(myEmail!!)
        playOnline(splitString(userEmail)+splitString(myEmail!!))
        acButton.isEnabled=false
        var reButton=findViewById<Button>(R.id.buRequest)
        reButton.isEnabled=false
        StartClickEvent()
    }

    fun RequestEvent(view: View) {
        playerSymbol=1
        var reButton=view as Button
        var userEmail:String?=null
        var tvEmail=findViewById<TextView?>(id.etEmail)
        if(tvEmail!=null){
            userEmail=tvEmail.text.toString().trim()
        }
        reButton.isEnabled=false
        var acButton=findViewById<Button>(R.id.buAccept)
        acButton.isEnabled=false
        tvEmail!!.text=""
        Toast.makeText(this,"Request sent!!",Toast.LENGTH_SHORT).show()
        myRef.child("user").child(splitString(userEmail!!)).child("Request").push().setValue(myEmail!!)
        playOnline(splitString(myEmail!!)+splitString(userEmail))
    }

    fun splitString(str:String):String{

        var split = str.split("@")
        return split[0]
    }

    var sessionID:String?=null
    var playerSymbol:Int?=null

    fun playOnline(sessionID:String){
        this.sessionID=sessionID
        myRef.child("playerOnline").child(sessionID).setValue(true)
        myRef.child("playerOnline").child(sessionID).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    val dt=snapshot.value as HashMap<String,Any>
                    if(dt!=null){
                        var value:String
                        for(key in dt.keys){
                            value=dt[key].toString()

                            if(value==myEmail){

                            }
                            else{
                                autoPlay(key.toInt())
                            }
                            myRef.child("playerOnline").child(sessionID).setValue(true)

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

    var lost=false
    fun checkWin2(){
        myRef.child("playerOnline").child(sessionID.toString()).addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    val dt=snapshot.value as HashMap<String,Any>
                    if(dt!=null){
                        for(key in dt.keys){
                            var tvTurn=findViewById<TextView>(R.id.tvTurn)
                            var endBtn=findViewById<Button>(R.id.buEnd)
                            if(key!=splitString(myEmail!!)&&dt[key].toString().equals("yes")){
                                lost=true
                                if(lost&&count1>=5){
                                    tvTurn.text="DRAW!!!"
                                    myRef.child("playerOnline").child(sessionID.toString()).child(splitString(myEmail!!)).setValue("drawn")
//                                    Toast.makeText(this@MainActivity,"GAME DRAW!!!",Toast.LENGTH_LONG).show()
                                    endGame(endBtn)

                                }
                                else if(lost&&count1>=5){
                                    tvTurn.text="YOU WON!!!"
                                    myRef.child("playerOnline").child(sessionID.toString()).child(splitString(myEmail!!)).setValue("lost")
//                                    Toast.makeText(this@MainActivity,"YOU WON!!!  ",Toast.LENGTH_LONG).show()
                                    endGame(endBtn)
                                }
                                else if(lost&&count1<5){
                                    tvTurn.text="YOU LOST!!!"
                                    myRef.child("playerOnline").child(sessionID.toString()).child(splitString(myEmail!!)).setValue("won")
//                                    Toast.makeText(this@MainActivity,"YOU LOST!!!",Toast.LENGTH_LONG).show()
                                    endGame(endBtn)
                                }
                            }
                            else if(key!=splitString(myEmail!!)&&dt[key].toString().equals("won")){
                                tvTurn.text="YOU WON!!"
//                                Toast.makeText(this@MainActivity,"YOU WON!!!  ",Toast.LENGTH_LONG).show()
                                endGame(endBtn)
                            }

                            else if(key!=splitString(myEmail!!)&&dt[key].toString().equals("lost")){
                                tvTurn.text="YOU LOST!!"
//                                Toast.makeText(this@MainActivity,"YOU LOST!!!  ",Toast.LENGTH_LONG).show()
                                endGame(endBtn)
                            }

                            else if(key!=splitString(myEmail!!)&&dt[key].toString().equals("drawn")){
                                tvTurn.text="GAME DRAWN!!"
//                                Toast.makeText(this@MainActivity,"GAME DRAWN!!!  ",Toast.LENGTH_LONG).show()
                                endGame(endBtn)
                            }
                            myRef.child("playerOnline").child(sessionID.toString()).child(splitString(myEmail!!)).removeValue()
                        }
                    }
                }
                catch (ex:java.lang.Exception){
                    Log.e("error message","Event Listener",ex)
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun autoPlay(number:Int){
        var buSelected: Button?=null
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
            if (buSelected!!.text.equals(number.toString())) {
                buSelected.setBackgroundColor(buSelected.resources.getColor(R.color.black))
                buSelected.setTextColor(buSelected.resources.getColor(R.color.white))
                buSelected.isEnabled = false
                player1.add(i)
                var turnText=findViewById<TextView>(R.id.tvTurn)
                turnText.text="YOURS TURN"
                checkwin()
                break
            }
        }
    }

    fun endGame(view: View) {
        var endBtn=view as Button
        endBtn.isVisible=false
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
        }

        for(i in 1..5){
            when(i){
                1 -> buSelected = findViewById(id.buttonB)
                2 -> buSelected = findViewById(id.buttonI)
                3 -> buSelected = findViewById(id.buttonN)
                4 -> buSelected = findViewById(id.buttonG)
                5 -> buSelected = findViewById(id.buttonO)
            }
            buSelected.setBackgroundColor(buSelected.resources.getColor(R.color.yellow))
            buSelected.isEnabled = false
        }
        player1.clear()

        var bingoLayout=findViewById<TableLayout>(id.bingoLayout)
        bingoLayout.isVisible=false

        var connLayout=findViewById<LinearLayout>(R.id.connectionLayout)
        connLayout.isVisible=true

        var acButton=findViewById<Button>(R.id.buAccept)
        acButton.isEnabled=true

        var reButton=findViewById<Button>(R.id.buRequest)
        reButton.isEnabled=true

        var tvEmail=findViewById<TextView?>(id.etEmail)
        tvEmail!!.text=""



    }
}