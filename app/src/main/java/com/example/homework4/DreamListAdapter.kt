package com.example.homework4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class DreamListAdapter : ListAdapter<Dream, DreamListAdapter.DreamViewHolder>(DreamComparator()){




    class DreamComparator:DiffUtil.ItemCallback<Dream>() {
        override fun areItemsTheSame(oldItem: Dream, newItem: Dream): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Dream, newItem: Dream): Boolean {

            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DreamViewHolder {
        return DreamViewHolder.create(parent)
    }


    override fun onBindViewHolder(holder: DreamListAdapter.DreamViewHolder, position: Int) {
        val currentDream = getItem(position)
        holder.bindText(currentDream.name, holder.textViewName)
        holder.bindText(currentDream.date, holder.textViewDate)

        holder.itemView.setOnClickListener{
            val currentContext = holder.itemView.context
            val intent = Intent(currentContext, DetailsActivity::class.java)
            intent.putExtra("id", currentDream.id.toString())
            currentContext.startActivity(intent)
        }

    }

    class DreamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textView_name)
        val textViewDate: TextView = itemView.findViewById(R.id.textView_date)

        fun bindText(text:String?, textView: TextView) {
            textView.text = text
        }

        companion object {
            fun create (parent: ViewGroup) : DreamViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dream, parent, false)
                return DreamViewHolder(view)
            }
        }

    }
}
