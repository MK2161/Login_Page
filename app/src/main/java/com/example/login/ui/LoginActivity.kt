package com.example.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import com.example.login.data.RetrofitClient
import com.example.login.model.CredentialModel
import com.example.login.model.LoginResponse
import com.example.login.preferenceManager.PreferenceManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val retrofitClient by lazy {
        RetrofitClient(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpListener()
    }

    private fun setUpListener() {
        uiBtnLogin.setOnClickListener {
            if (validateNames()) {
                loginVerifyApi()
            }
        }
        uiBtnResetPassword.setOnClickListener {
            onResetPassword()
        }
       
    }

    private fun validateNames(): Boolean {
        return if (uiEtName.text.isNullOrEmpty()) {
            Toast.makeText(this, "EnterName", Toast.LENGTH_LONG).show()
            false
        } else if (uiEtPassword.text.isNullOrEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG).show()
            false
        } else if (uiEtEmail.text == null) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_LONG).show()
            false
        } else true
    }

    private fun loginVerifyApi() {
        val credentialModel = CredentialModel(
            email = uiEtEmail.text.toString(),
            password = uiEtPassword.text.toString(),
            deviceType = "android",
            userType = "staff"
        )


        retrofitClient.createAdapter().login(credentialModel)
            .enqueue(object : Callback<LoginResponse?> {
                override fun onResponse(
                    call: Call<LoginResponse?>,
                    response: Response<LoginResponse?>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        saveUserDetail(response.body())
                        onLoginSuccess()
                    } else {
                        uiEtPassword.text?.clear()
                    }
                }

                override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                    Log.d("loginActivity", "onFailure $t")
                }
            })
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, DocumentActivity::class.java)
        startActivity(intent)
    }

    private fun onResetPassword() {
        val intent = Intent(this, ForgotActivity::class.java)
        startActivity(intent)
    }


    private fun saveUserDetail(loginResponse: LoginResponse?) {
        val preferenceManager = PreferenceManager(this)
        preferenceManager.setStaffId(loginResponse?.data?.staffId.toString())
        preferenceManager.setAccessToken(loginResponse?.data?.tokens?.accessToken?.token.toString())
        preferenceManager.setRefreshToken(loginResponse?.data?.tokens?.refreshToken?.token.toString())
    }
}
