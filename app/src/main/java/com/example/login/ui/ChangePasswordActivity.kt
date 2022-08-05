package com.example.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import com.example.login.data.RetrofitClient
import com.example.login.model.ChangePasswordModel
import com.example.login.model.ChangePasswordRequest
import com.example.login.model.Constants
import kotlinx.android.synthetic.main.activity_change_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {


    private  val retrofitClient by lazy{
        RetrofitClient(this)
    }
    private var verificationId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        setUpListener()
        verificationId = intent.getStringExtra(Constants.KEY_VERIFICATION_OTP)

    }

    private fun setUpListener() {
        uiBtnVerify.setOnClickListener {
            if (validatePassword()) {
                verifyPassword()
            }
        }
        uiIvClose.setOnClickListener {
            finish()
        }
    }

    private fun validatePassword(): Boolean {
        return if (uiEtChangePassword.text.isNullOrEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG).show()
            false
        } else if (uiEtConfirmPassword.text.isNullOrEmpty()) {
            Toast.makeText(this, "Enter Confirm Password", Toast.LENGTH_LONG).show()
            false
        } else if (uiEtChangePassword.text.toString() != uiEtConfirmPassword.text.toString()) {
            Toast.makeText(this, " Password not matching ", Toast.LENGTH_LONG).show()
            false
        }else true
    }

    private fun verifyPassword() {
        val changePasswordRequest = ChangePasswordRequest(
            verificationId = verificationId.toString(),
            password = uiEtConfirmPassword.text.toString()
        )

        retrofitClient.createAdapter().changePassword(changePasswordRequest)
            .enqueue(object : Callback<ChangePasswordModel?> {
                override fun onResponse(
                    call: Call<ChangePasswordModel?>,
                    response: Response<ChangePasswordModel?>
                ) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        onSuccessPassword()
                    } else {
                        failurePassword(response.body()?.message.toString())
                    }
                }

                override fun onFailure(call: Call<ChangePasswordModel?>, t: Throwable) {
                    Log.d("ChangePassword", "onFailure $t")
                }
            })
    }

    private fun failurePassword(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun onSuccessPassword() {
        val intent = Intent(this, SuccessActivity::class.java)
        startActivity(intent)
        finish()
    }

}