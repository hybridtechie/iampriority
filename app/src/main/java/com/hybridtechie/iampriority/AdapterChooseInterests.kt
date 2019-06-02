package com.hybridtechie.iampriority

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class AdapterChooseInterests(items: List<Interests>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        val image: ImageView = v.findViewById(R.id.image) as ImageView

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
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }

    interface OnLoadMoreListener {
        fun onLoadMore(current_page: Int)
    }

}