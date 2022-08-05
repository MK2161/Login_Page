package com.example.login.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.Adapter.DocumentListAdapter
import com.example.login.R
import com.example.login.data.RetrofitClient
import com.example.login.model.DocumentItem
import com.example.login.model.DocumentListResponse
import com.example.login.preferenceManager.PreferenceManager
import kotlinx.android.synthetic.main.activity_document.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_notification.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocumentActivity : AppCompatActivity() {

    private val retrofitClient by lazy {
        RetrofitClient(this)
    }
    private val documentListAdapter by lazy {
        DocumentListAdapter(
            documentItem = arrayListOf(),
            onActionClicked = { onAction(it) }
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)
        setUpUi()
        onCallApi()
    }

    private fun setUpUi() {
        uiRvDocumentList.apply {
            adapter = documentListAdapter
        }
    }

    private fun onCallApi() {
        val preferenceManager = PreferenceManager(this)
        retrofitClient.createAdapter().getRecentDocumentList(
            userId = preferenceManager.getStaffId(),
            limit = 30,
            pageNumber = 2,
            isStarred = null
        ).enqueue(object : Callback<DocumentListResponse?> {
            override fun onResponse(
                call: Call<DocumentListResponse?>, response: Response<DocumentListResponse?>
            ) {
                if (response.isSuccessful && response.body()?.status == true) {
                    onDataSuccess(response.body())
                } else {
                    failureApi(response.message())
                }
            }

            override fun onFailure(call: Call<DocumentListResponse?>, t: Throwable) {
                Log.d("DocumentActivity", "onFailure $t")
            }
        })

    }

    private fun failureApi(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun onDataSuccess(documentListResponse: DocumentListResponse?) {
        if (documentListResponse?.documentList.isNullOrEmpty()){
            uiTvDocuments.visibility = View.VISIBLE
        }else{
            documentListResponse?.documentList?.let { documentList->
                documentListAdapter.setDocumentList(documentItemList = documentList) }
        }
    }

    private fun onAction(documentItem: DocumentItem) {
        Toast.makeText(this, "message$documentItem", Toast.LENGTH_SHORT).show()
    }
}