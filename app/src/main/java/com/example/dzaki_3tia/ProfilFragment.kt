package com.example.dzaki_3tia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dzaki_3tia.databinding.FragmentProfilBinding

class ProfilFragment : Fragment() {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    private val menuProfilList = listOf(
        "Informasi Akun Warga",
        "Nomor Darurat Keluarga",
        "Riwayat Laporan Layanan",
        "Pengaturan Notifikasi Darurat",
        "Bahasa Aplikasi (Indonesia)",
        "Panduan Penggunaan CIVIKA",
        "Pusat Bantuan Pemerintah Kota",
        "Tentang Aplikasi CIVIKA v1.0"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Profil Warga"

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            menuProfilList
        )

        binding.listViewProfil.adapter = adapter

        binding.listViewProfil.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = menuProfilList[position]
            Toast.makeText(requireContext(), "Kamu memilih: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}