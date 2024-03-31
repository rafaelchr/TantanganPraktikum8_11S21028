package com.ifs21028.mygmail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21028.mygmail.databinding.ItemRowMailBinding

class MailAdapter(private val listMail: ArrayList<Mail>) :
    RecyclerView.Adapter<MailAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowMailBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val mail = listMail[position]
        holder.binding.ivItemMail.setImageResource(mail.icon)
        holder.binding.tvItemSender.text = mail.sender
        holder.binding.tvItemTitle.text = mail.title
        holder.binding.tvItemCont.text = mail.content
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMail[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listMail.size

    class ListViewHolder(var binding: ItemRowMailBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Mail)
    }
}