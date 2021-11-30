package com.example.android_lab6.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_lab6.R
import com.example.android_lab6.data.entity.Person

class PersonViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val userName: TextView = view.findViewById(R.id.nameValueTextView)
    private val comment: TextView = view.findViewById(R.id.commentValueTextView)

    fun bindView(item: Person) {
        userName.text = "${item.name} ${item.surname}"
        comment.text = item.comment
    }
}