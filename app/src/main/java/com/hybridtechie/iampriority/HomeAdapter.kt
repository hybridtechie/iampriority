package com.hybridtechie.iampriority


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class HomeAdapter(
    private val ctx: Context, items: List<Alerts>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = ArrayList<Alerts>()

    private var mOnItemClickListener: OnItemClickListener? = null

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Alerts, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mItemClickListener
    }

    init {
        this.items = items as ArrayList<Alerts>
    }

    inner class OriginalViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var image: ImageView = v.findViewById(R.id.image)
        var title: TextView = v.findViewById(R.id.title)
        var subtitle: TextView = v.findViewById(R.id.subtitle)
        var date: TextView = v.findViewById(R.id.date)
        var lyt_parent: View = v.findViewById(R.id.lyt_parent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh: RecyclerView.ViewHolder
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news_horizontal, parent, false)
        vh = OriginalViewHolder(v)
        return vh
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OriginalViewHolder) {
            val n = items[position]
            holder.title.text = n.name
            holder.subtitle.text = n.category
            holder.date.text = n.imageUrl
            holder.image.setImageResource(items[position].image)
            holder.lyt_parent.setOnClickListener(View.OnClickListener { view ->
                if (mOnItemClickListener == null) return@OnClickListener
                mOnItemClickListener!!.onItemClick(view, items[position], position)
            })
        }
    }

}