package com.example.dzaki_3tia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dzaki_3tia.databinding.FragmentFavoritBinding

class FavoritFragment : Fragment() {
    private var _binding: FragmentFavoritBinding? = null
    private val binding get() = _binding!!

    // Minimal 8 data dengan deskripsi sesuai Latihan Mandiri 5.2 & tema projek CIVIKA
    private val dataListWithDesc = listOf(
        mapOf("title" to "Call Center Darurat 112", "desc" to "Layanan panggilan darurat terpadu bebas pulsa 24 jam"),
        mapOf("title" to "IGD RSUD Arifin Achmad", "desc" to "Unit gawat darurat rujukan utama Provinsi Riau"),
        mapOf("title" to "Layanan E-KTP & KK (MPP)", "desc" to "Pengurusan dokumen kependudukan di Mal Pelayanan Publik"),
        mapOf("title" to "SPKT Polresta Pekanbaru", "desc" to "Sentra pelayanan kepolisian terpadu & surat kehilangan"),
        mapOf("title" to "Pemadam Kebakaran (113)", "desc" to "Tanggap darurat kebakaran dan penyelamatan warga kota"),
        mapOf("title" to "Trans Metro Pekanbaru", "desc" to "Informasi koridor dan halte bus raya terpadu Pekanbaru"),
        mapOf("title" to "Samsat Kota Pekanbaru", "desc" to "Pelayanan pajak kendaraan bermotor tahunan dan 5 tahunan"),
        mapOf("title" to "PMI Kota Pekanbaru", "desc" to "Layanan donor darah dan ketersediaan stok kantong darah")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Layanan Favorit"

        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewItems.adapter = adapter

        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(requireContext(), "Kamu memilih: $title ($desc)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}