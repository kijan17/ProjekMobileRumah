package com.example.dzaki_3tia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dzaki_3tia.databinding.ItemUsulanBinding

class UsulanAdapter(
    private val usulanList: List<UsulanModel>,
    private val onItemClick: (UsulanModel) -> Unit
) : RecyclerView.Adapter<UsulanAdapter.UsulanViewHolder>() {

    inner class UsulanViewHolder(val binding: ItemUsulanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsulanViewHolder {
        val binding = ItemUsulanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsulanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsulanViewHolder, position: Int) {
        val item = usulanList[position]
        with(holder.binding) {
            tvJudulUsulan.text = item.judul
            tvDeskripsiUsulan.text = item.deskripsi
            tvTanggalUsulan.text = item.tanggal

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgUsulan)

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = usulanList.size
}