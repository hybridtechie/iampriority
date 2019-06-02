package com.hybridtechie.iampriority

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView
import java.util.*

class ChooseInterestsAdapter(items: List<Interests>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = ArrayList<Interests>()

    private var onLoadMoreListener: OnLoadMoreListener? = null
    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Int?, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mItemClickListener
    }

    init {
        this.items = items as ArrayList<Interests>
    }

    inner class OriginalViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image: CircularImageView = v.findViewById(R.id.image) as CircularImageView
        val title: TextView = v.findViewById(R.id.text) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_image, parent, false)
        vh = OriginalViewHolder(v)
        return vh
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OriginalViewHolder) {
            holder.image.setImageResource(items[position].image)
            holder.title.text = items[position].name
            holder.image.setOnClickListener { v -> select(v, items[position]) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }

    private fun select(v: View, item: Interests) {
        v as CircularImageView
        if (!item.selected) {
            item.selected = true
            v.setBorderColor(Color.GRAY)
            v.setBorderWidth(10f)
        } else {
            item.selected = false
            v.setBorderColor(Color.TRANSPARENT)
            v.setBorderWidth(0f)
        }
    }

    interface OnLoadMoreListener {
        fun onLoadMore(current_page: Int)
    }

}