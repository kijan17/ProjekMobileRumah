package com.example.dzaki_3tia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dzaki_3tia.databinding.ItemFacilityBinding

class FacilityAdapter(
    private var facilityList: List<FacilityModel>,
    private val onItemClick: (FacilityModel) -> Unit
) : RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>() {

    inner class FacilityViewHolder(val binding: ItemFacilityBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val binding = ItemFacilityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FacilityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val item = facilityList[position]
        with(holder.binding) {
            tvNama.text = item.nama
            tvKategori.text = item.kategori
            tvDeskripsi.text = item.deskripsi
            // Load gambar pakai Glide
            Glide.with(holder.itemView.context).load(item.imageUrl).into(imgFacility)

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = facilityList.size

    // Fungsi tambahan untuk mengupdate list saat difilter
    fun updateData(newList: List<FacilityModel>) {
        facilityList = newList
        notifyDataSetChanged()
    }
}