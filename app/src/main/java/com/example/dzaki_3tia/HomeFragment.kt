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

        // Pasang Toolbar sesuai Modul 2.2
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "CIVIKA - Beranda"

        // Latihan Mandiri 5.1: Ambil username dari SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Warga")
        binding.tvGreeting.text = "HALO, ${username?.uppercase()}!"

        // Klik Kategori
        binding.cardCatKesehatan.setOnClickListener {
            Toast.makeText(requireContext(), "Kategori: Kesehatan", Toast.LENGTH_SHORT).show()
        }
        binding.cardCatKeamanan.setOnClickListener {
            Toast.makeText(requireContext(), "Kategori: Keamanan", Toast.LENGTH_SHORT).show()
        }
        binding.cardCatTransportasi.setOnClickListener {
            Toast.makeText(requireContext(), "Kategori: Transportasi", Toast.LENGTH_SHORT).show()
        }
        binding.cardCatAdministrasi.setOnClickListener {
            Toast.makeText(requireContext(), "Kategori: Administrasi", Toast.LENGTH_SHORT).show()
        }

        // Kontak Darurat
        binding.btnAmbulans.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:119")))
        }
        binding.btnPolisi.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:110")))
        }

        // Fungsi Logout sesuai Modul halaman 9-10
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