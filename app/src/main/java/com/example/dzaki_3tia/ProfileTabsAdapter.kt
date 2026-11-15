package com.example.dzaki_3tia

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfilTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UsulanFragment() // Buat Fragment Blank baru bernama UsulanFragment
            1 -> InfoAkunFragment() // Buat Fragment Blank baru bernama InfoAkunFragment
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}