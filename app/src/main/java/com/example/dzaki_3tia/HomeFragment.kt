package com.example.dzaki_3tia

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dzaki_3tia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Ambil nama user dari SharedPreferences saat Login
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Warga")
        binding.tvGreeting.text = "Halo, $username!"

        // 2. Klik Ikon Info atau Banner -> Buka TutorialMessageActivity yang sudah ada
        val bukaTutorial = View.OnClickListener {
            startActivity(Intent(requireContext(), TutorialMessageActivity::class.java))
        }
        binding.btnTutorial.setOnClickListener(bukaTutorial)
        binding.cardBanner.setOnClickListener(bukaTutorial)

        // 3. Klik Search Bar atau "Lihat Direktori" -> Pindah ke Tab Cari
        val pindahKeCari = View.OnClickListener {
            (activity as? HomeActivity)?.pindahKeTabCari()
        }
        binding.cardSearchShortcut.setOnClickListener(pindahKeCari)
        binding.tvViewAll.setOnClickListener(pindahKeCari)

        // 4. Klik Tombol Darurat -> Langsung mengarah ke Telepon HP (ACTION_DIAL)
        binding.btnAmbulans.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:119")))
        }

        binding.btnPolisi.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:110")))
        }

        // 5. Klik Kartu Web -> Langsung membuka Browser ke Web Resmi (ACTION_VIEW)
        binding.btnPortalPemko.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://pekanbaru.go.id")))
        }

        binding.btnMppWeb.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://mpp.pekanbaru.go.id")))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}