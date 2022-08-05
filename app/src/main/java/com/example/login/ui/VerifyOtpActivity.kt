package com.example.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import com.example.login.data.RetrofitClient
import com.example.login.model.Constants
import com.example.login.model.Constants.KEY_VERIFICATION_OTP
import com.example.login.model.VerifyOtpModel
import com.example.login.model.VerifyOtpRequest
import kotlinx.android.synthetic.main.activity_verify_otp.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerifyOtpActivity :AppCompatActivity() {

    private  val retrofitClient by lazy{
        RetrofitClient(this)
    }
    private var id:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        setUpListener()
        id = intent.getStringExtra(Constants.KEY_VERIFICATION_ID)


    }

    private fun setUpListener(){
        uiBtnVerify.setOnClickListener{
          if (verifiedNumbers()) {
              verifyOtp()
          }
        }
        uiIvCancel.setOnClickListener{
            finish()
        }
    }

    private fun verifiedNumbers():Boolean {
       return if (uiEtVerificationCode.text.toString().length == 4) {
            true
        } else {
            Toast.makeText(this, "Invalid otp ", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private  fun verifyOtp(){
        val verifyOtpRequest = VerifyOtpRequest(
            id = id.toString(),
            otp = uiEtVerificationCode.text.toString()
        )
        retrofitClient.createAdapter().verifyOtp(verifyOtpRequest).enqueue(object :Callback<VerifyOtpModel>{
            override fun onResponse(call: Call<VerifyOtpModel>, response: Response<VerifyOtpModel>) {
                if (response.isSuccessful && response.body()?.status == true)  {
                    onChangePassword()
                }else{
                    apiFailure(response.body()?.message.toString())
               }
            }

            override fun onFailure(call: Call<VerifyOtpModel>, t: Throwable) {
                Log.d("verifyOtpActivity","onFailure $t")
            }
        })
    }

    private fun apiFailure(message:String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    }

   private fun onChangePassword(){
       val intent = Intent(this, ChangePasswordActivity::class.java)
       intent.putExtra(KEY_VERIFICATION_OTP,id)
       startActivity(intent)
       finish()
   }
}