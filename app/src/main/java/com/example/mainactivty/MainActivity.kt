package com.example.mainactivty

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResult.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MoveForResult.EXTRA_SELECTED_VALUE,0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        val btnMoveWithObject: Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvResult = findViewById<TextView>(R.id.tv_result)
    }

    override fun onClick(view: View) {
        when(view?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, Move::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_activity_data -> {
            val moveWithDataIntent = Intent(this@MainActivity,MoveWithData::class.java)
            moveWithDataIntent.putExtra(MoveWithData.EXTRA_NAME, "Rindang Bani Isyan")
            moveWithDataIntent.putExtra(MoveWithData.EXTRA_AGE, 19)
            startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "Rindang Bani Isyan",
                    19,
                    "rindang90@gmail.com",
                    "Karawang"
                )
                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObject::class.java)
                moveWithObjectIntent.putExtra(MoveWithObject.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }
            R.id.btn_dial_number -> {
                val phoneNumber = "081210841382"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResult::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }
}