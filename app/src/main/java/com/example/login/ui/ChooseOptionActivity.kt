package com.example.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import com.example.login.data.RetrofitClient
import com.example.login.model.Constants
import com.example.login.model.Constants.KEY_VERIFICATION_ID
import com.example.login.model.RequestOtpResponse
import kotlinx.android.synthetic.main.activity_choose_option.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChooseOptionActivity : AppCompatActivity() {

    private  val retrofitClient by lazy{
        RetrofitClient(this)
    }
    private var email:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_option)
        setUpListener()
        email = intent.getStringExtra(Constants.KEY_EMAIL_ID)
        Log.d("ChooseOption","my email is $email" )
    }

    private fun setUpListener() {
        uiBtnCallMobile.setOnClickListener {
            chooseOnApi("mobile")
        }
        uiBtnCallEmail.setOnClickListener {
            chooseOnApi("email")
        }
        uiIvEnd.setOnClickListener {
            finish()
        }
    }

    private fun chooseOnApi(option: String) {
        val rbEmail: RequestBody = email.toString().toRequestBody(MultipartBody.FORM)
        val rbValidationMode: RequestBody = option.toRequestBody(MultipartBody.FORM)
        val rbUserType: RequestBody = "staff" .toRequestBody(MultipartBody.FORM)

        retrofitClient.createAdapter().requestOtp(
            email = rbEmail,
            mode = rbValidationMode,
            userType = rbUserType
        ).enqueue(object : Callback<RequestOtpResponse?> {
            override fun onResponse(
                call: Call<RequestOtpResponse?>,
                response: Response<RequestOtpResponse?>
            ) {
                if (response.isSuccessful && response.body()?.status == true) {
                    onNavigationOtp(response.body()!!.data?.id.toString())
                }else{
                    onApiFailure(response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<RequestOtpResponse?>, t: Throwable) {
                Log.d("chooseOption", "onFailure $t")
            }

        })
    }

    private fun onApiFailure(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun onNavigationOtp(verificationId:String) {
        val intent = Intent(this, VerifyOtpActivity::class.java)
        intent.putExtra(KEY_VERIFICATION_ID,verificationId)
        startActivity(intent)
        finish()
    }
}

