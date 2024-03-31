package com.ifs21028.mygmail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21028.mygmail.databinding.ItemRowVideoBinding

class VideoAdapter(private val listVideo: ArrayList<Video>) :
    RecyclerView.Adapter<VideoAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowVideoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val video = listVideo[position]
        holder.binding.ivItemVideo.setImageResource(video.icon)
        holder.binding.tvItemName.text = video.name
        holder.binding.tvItemTime.text = video.time
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listVideo[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listVideo.size

    class ListViewHolder(var binding: ItemRowVideoBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Video)
    }
}