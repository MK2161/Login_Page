package com.example.login.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.Adapter.DocumentListAdapter.DocumentListViewHolder
import com.example.login.R
import com.example.login.model.DocumentItem
import kotlinx.android.synthetic.main.activity_document_list.view.*

class DocumentListAdapter(
    private val documentItem: ArrayList<DocumentItem>,
    private val onActionClicked: (DocumentItem) -> Unit,
) : RecyclerView.Adapter<DocumentListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_document_list, parent, false)
        return DocumentListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DocumentListViewHolder, position: Int) {
        val inputDocumentResponse = documentItem[position]
        with(holder) {
            uiTvDocumentName.text = inputDocumentResponse.name
            uiTvExt.text = inputDocumentResponse.type
            uiTvExtSmall.text = inputDocumentResponse.type
            uiTvDocumentDetails.text = inputDocumentResponse.documentId
            uiTvDocumentDate.text = inputDocumentResponse.updatedDate

        }
    }

    override fun getItemCount(): Int {
        return documentItem.size
    }

    inner class DocumentListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val uiIvDocument: AppCompatImageView = view.uiIvDocument
        val uiIvDocumentIcon: AppCompatImageView = view.uiIvDocumentIcon
        val uiTvDocumentName: AppCompatTextView = view.uiTvDocumentName
        val uiTvExt: AppCompatTextView = view.uiTvExt
        val uiTvExtSmall: AppCompatTextView = view.uiTvExtSmall
        val uiTvDocumentDetails: AppCompatTextView = view.uiTvDocumentDetails
        val uiTvDocumentDate: AppCompatTextView = view.uiTvDocumentDate

        init {
            uiIvDocumentIcon.setOnClickListener {
                onActionClicked.invoke(documentItem[adapterPosition])
            }
        }

        }
    fun setDocumentList(documentItemList: ArrayList<DocumentItem>) {
        documentItem.clear()
        documentItem.addAll(documentItemList)
        notifyDataSetChanged()

    }

}


