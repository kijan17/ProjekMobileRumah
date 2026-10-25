package com.example.dzaki_3tia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.dzaki_3tia.databinding.ItemFacilityBinding
import com.google.android.material.snackbar.Snackbar

class FacilityAdapter(
    context: Context,
    private val facilities: List<FacilityModel>
) : ArrayAdapter<FacilityModel>(context, 0, facilities) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemFacilityBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = facilities[position]

        Glide.with(context)
            .load(data.imageUrl)
            .placeholder(R.drawable.logo_civika)
            .error(R.drawable.logo_civika)
            .into(binding.avatarImg)

        binding.textSender.text = data.name
        binding.textMessage.text = data.address
        binding.textTime.text = data.statusTime

        view.setOnClickListener {
            Snackbar.make(
                parent,
                "${data.name} - ${data.address} (${data.statusTime})",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}