package com.example.login.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.Adapter.ListShowAdapter
import com.example.login.R
import com.example.login.data.RetrofitClient
import com.example.login.model.NotificationItem
import com.example.login.model.NotificationResponse
import com.example.login.preferenceManager.PreferenceManager
import kotlinx.android.synthetic.main.activity_notification.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationActivity : AppCompatActivity() {

    private val retrofitClient by lazy {
        RetrofitClient(this)
    }
    private val listShowAdapter by lazy {
        ListShowAdapter(
            notificationItem = arrayListOf(),
            onActionClicked = { onActionClicked(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        setUpUI()
        onCallApi()
    }

    private fun setUpUI() {
        uiRvList.apply {
            adapter = listShowAdapter
        }
    }


    private fun onCallApi() {
        val preferenceManager = PreferenceManager(this)
        retrofitClient.createAdapter().getNotifications(
            userId = preferenceManager.getStaffId(),
            limit = 10,
            pageNumber = 1
        ).enqueue(object : Callback<NotificationResponse?> {
            override fun onResponse(
                call: Call<NotificationResponse?>, response: Response<NotificationResponse?>
            ) {
                if (response.isSuccessful && response.body()?.status == true) {
                    onSuccessApi(response.body())
                    Log.d("ChooseOption", "my result is ${onSuccessApi(response.body())}")
                } else {
                    failureApi(response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<NotificationResponse?>, t: Throwable) {
                Log.d("NotificationActivity", "onFailure $t")
            }

        })
    }

    private fun failureApi(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun onSuccessApi(notificationResponse: NotificationResponse?) {
       if (notificationResponse?.data.isNullOrEmpty()){
           uiTvNoData.visibility = View.VISIBLE
       }else{
           notificationResponse?.data?.let { listShowAdapter.setNotificationItemList(it) }
       }
    }

    private fun onActionClicked(notificationItem: NotificationItem) {
        Toast.makeText(this,"message$notificationItem",Toast.LENGTH_SHORT).show()
    }
}