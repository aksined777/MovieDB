package com.themoviedb.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.themoviedb.domain.entity.Move
import com.themoviedb.presentation.R

class MoveAdapter : RecyclerView.Adapter<MoveAdapter.MoveViewHolder>() {
    private var list: List<Move> = listOf()
    var listener: OnItemClick? = null

    fun setData(data: List<Move>) {
        list = data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClick){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        return MoveViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.move_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount(): Int = list.size

    inner class MoveViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(move: Move) {
            itemView?.apply {
                findViewById<TextView>(R.id.name_move).text = move.name
                val path = "http://image.tmdb.org/t/p/w500${move.poster}"
                Glide.with(itemView)
                    .load(path)
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.ic_baseline_cancel_presentation_24)
                    .into(findViewById<ImageView>(R.id.iv_poster))

                setOnClickListener {
                    listener?.onClick(move)
                }
            }
        }
    }

    fun interface OnItemClick {
        fun onClick(move: Move)
    }
}
