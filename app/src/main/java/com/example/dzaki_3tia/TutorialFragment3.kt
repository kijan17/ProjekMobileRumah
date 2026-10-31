package com.example.dzaki_3tia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Tutorial3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil tombol dan tutup halaman tutorial sesuai Latihan Mandiri 7.4 Modul 9
        val btnMulai = view.findViewById<Button>(R.id.btnMulai)
        btnMulai.setOnClickListener {
            requireActivity().finish()
        }
    }
}