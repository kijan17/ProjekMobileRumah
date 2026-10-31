package com.example.dzaki_3tia

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dzaki_3tia.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Warga")
        binding.tvGreeting.text = "HALO, ${username?.uppercase()}!"

        // Listener ChipGroup sesuai Modul 9
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Listener 6 Tombol GridLayout
        binding.btnGridKesehatan.setOnClickListener {
            Toast.makeText(requireContext(), "Layanan: Fasilitas Kesehatan", Toast.LENGTH_SHORT).show()
        }
        binding.btnGridKeamanan.setOnClickListener {
            Toast.makeText(requireContext(), "Layanan: Pos Keamanan Kota", Toast.LENGTH_SHORT).show()
        }
        binding.btnGridTransportasi.setOnClickListener {
            Toast.makeText(requireContext(), "Layanan: Rute Bus Trans Metro", Toast.LENGTH_SHORT).show()
        }
        binding.btnGridDukcapil.setOnClickListener {
            Toast.makeText(requireContext(), "Layanan: Administrasi Disdukcapil", Toast.LENGTH_SHORT).show()
        }
        binding.btnGridPajak.setOnClickListener {
            Toast.makeText(requireContext(), "Layanan: Informasi Pajak Daerah", Toast.LENGTH_SHORT).show()
        }
        binding.btnGridCCTV.setOnClickListener {
            Toast.makeText(requireContext(), "Layanan: Pantauan CCTV Jalan Raya", Toast.LENGTH_SHORT).show()
        }

        // Kontak Darurat
        binding.btnAmbulans.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:119")))
        }
        binding.btnPolisi.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:110")))
        }

        // Logout
        val logoutAction = {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()
                    dialog.dismiss()

                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        binding.btnLogout.setOnClickListener { logoutAction() }
        binding.btnProfile.setOnClickListener { logoutAction() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}