package com.example.dzaki_3tia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dzaki_3tia.databinding.FragmentProfilBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProfilFragment : Fragment() {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // 1. Aksi Tombol Logout (Hapus session login agar kembali ke halaman Login)
        binding.btnLogout.setOnClickListener {
            sharedPref.edit().clear().apply()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        // 2. Pasang Adapter ViewPager
        val tabsAdapter = ProfilTabsAdapter(this)
        binding.viewPagerProfil.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 (Sesuai Modul P10)
        TabLayoutMediator(binding.tabLayoutProfil, binding.viewPagerProfil) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Kontak & Portal"
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 4 // Jumlah kontak penting
                }
                1 -> tab.text = "Info Aplikasi"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}