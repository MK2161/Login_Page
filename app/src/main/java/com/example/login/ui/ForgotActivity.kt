package com.example.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        uiTvBest.text.toString()

    }
}