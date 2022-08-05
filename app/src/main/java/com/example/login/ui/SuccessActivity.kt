package com.example.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import com.example.login.model.Constants
import kotlinx.android.synthetic.main.activity_success.*

class SuccessActivity:AppCompatActivity() {

    private var correctPassword:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        setUpListener()

    }
    private fun setUpListener() {
        uiBtnSignIn.setOnClickListener{
            finish()
        }
        uiIvBack.setOnClickListener{
            finish()
        }
    }
}