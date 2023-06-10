package com.example.firebaseapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.firebaseapp.R
import com.example.firebaseapp.models.History

class HistoryAdapter(context: Context, private val resource: Int, private val historyList: List<History>) :
    ArrayAdapter<History>(context, resource, historyList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resource, parent, false)
            viewHolder = ViewHolder()
            viewHolder.image = view.findViewById(R.id.r_image)
            viewHolder.title = view.findViewById(R.id.r_title)
            viewHolder.year = view.findViewById(R.id.r_yer)
            viewHolder.notes = view.findViewById(R.id.r_notes)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val history = historyList[position]
        viewHolder.title?.text = history.title
        viewHolder.year?.text = history.yer
        viewHolder.notes?.text = history.notes


        Glide.with(context).load(history.imageurl).into(viewHolder.image!!)

        return view
    }

    private class ViewHolder {
        var image: ImageView? = null
        var title: TextView? = null
        var year: TextView? = null
        var notes: TextView? = null
    }
}
