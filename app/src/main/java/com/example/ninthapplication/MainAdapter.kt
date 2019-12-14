package com.example.ninthapplication

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter


class MainAdapter(data: OrderedRealmCollection<Record>) :
    RealmRecyclerViewAdapter<Record, MainAdapter.ViewHolder>(data, true) {

    init {
        setHasStableIds(true)
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val date: TextView = cell.findViewById(android.R.id.text1)
        val timeBegin: TextView = cell.findViewById(android.R.id.text2)
        val timeFinish: TextView = cell.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val record: Record? = getItem(position)
        holder.date.text = DateFormat.format("yyyy/MM/dd", record?.date)
        holder.timeBegin.text = record?.timeBegin.toString()
        holder.timeFinish.text = record?.timeFinish.toString()
    }

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id ?: 0
    }
}