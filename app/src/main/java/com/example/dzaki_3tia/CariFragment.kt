package com.example.dzaki_3tia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzaki_3tia.databinding.FragmentCariBinding

class CariFragment : Fragment() {
    private var _binding: FragmentCariBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FacilityAdapter

    // Data Master lengkap dengan Nomor Telepon dan Link Web Resmi
    private val allData = listOf(
        FacilityModel(
            "RSUD Arifin Achmad", "Kesehatan",
            "Jl. Diponegoro No.2, Pekanbaru (Buka 24 Jam)",
            "https://picsum.photos/seed/rsud/400/300",
            "076121618", "https://rsudarifinachmad.riau.go.id"
        ),
        FacilityModel(
            "Puskesmas Rumbai", "Kesehatan",
            "Jl. Sekolah No.54, Meranti Pandak, Rumbai",
            "https://picsum.photos/seed/puskes/400/300",
            "076151015", "https://dinkes.pekanbaru.go.id"
        ),
        FacilityModel(
            "Polresta Pekanbaru", "Keamanan",
            "Jl. Jend. Ahmad Yani No.11, Pekanbaru",
            "https://picsum.photos/seed/polres/400/300",
            "110", "https://polrestapekanbaru.com"
        ),
        FacilityModel(
            "Pemadam Kebakaran Kota", "Keamanan",
            "Jl. Cempaka No.31, Sukajadi, Pekanbaru",
            "https://picsum.photos/seed/damkar/400/300",
            "113", "https://pekanbaru.go.id"
        )
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCariBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Saat item diklik, munculkan pilihan Telepon atau Buka Website
        adapter = FacilityAdapter(allData) { item ->
            val opsi = arrayOf("Hubungi Telepon (${item.telepon})", "Kunjungi Website Resmi")
            AlertDialog.Builder(requireContext())
                .setTitle(item.nama)
                .setItems(opsi) { _, which ->
                    when (which) {
                        0 -> startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${item.telepon}")))
                        1 -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.webUrl)))
                    }
                }
                .setNegativeButton("Batal", null)
                .show()
        }

        binding.rvFasilitas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFasilitas.adapter = adapter

        // Filter ChipGroup
        binding.chipGroupKategori.setOnCheckedChangeListener { _, checkedId ->
            val filteredList = when (checkedId) {
                R.id.chipKesehatan -> allData.filter { it.kategori == "Kesehatan" }
                R.id.chipKeamanan -> allData.filter { it.kategori == "Keamanan" }
                else -> allData
            }
            adapter.updateData(filteredList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}