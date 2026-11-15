package com.example.dzaki_3tia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzaki_3tia.databinding.FragmentUsulanBinding

class UsulanFragment : Fragment() {
    private var _binding: FragmentUsulanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUsulanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listKontakPenting = listOf(
            UsulanModel(
                "Call Center Darurat 112",
                "Layanan panggilan darurat terpadu bebas pulsa Pemerintah Kota Pekanbaru.",
                "TELEPON: 112",
                "https://picsum.photos/seed/cc112/400/300",
                "tel:112"
            ),
            UsulanModel(
                "SAR / Basarnas Pekanbaru",
                "Bantuan pencarian dan pertolongan bencana darurat.",
                "TELEPON: 115",
                "https://picsum.photos/seed/sar/400/300",
                "tel:115"
            ),
            UsulanModel(
                "Dinas Kesehatan Pekanbaru",
                "Portal informasi layanan kesehatan masyarakat dan puskesmas.",
                "WEB: dinkes.pekanbaru.go.id",
                "https://picsum.photos/seed/dinkes/400/300",
                "https://dinkes.pekanbaru.go.id"
            ),
            UsulanModel(
                "Dinas Dukcapil Pekanbaru",
                "Layanan informasi administrasi kependudukan (KTP, KK, Akta).",
                "WEB: disdukcapil.pekanbaru.go.id",
                "https://picsum.photos/seed/dukcapil/400/300",
                "https://disdukcapil.pekanbaru.go.id"
            )
        )

        binding.rvUsulan.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsulan.adapter = UsulanAdapter(listKontakPenting) { item ->
            if (item.actionUrl.startsWith("tel:")) {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(item.actionUrl)))
            } else {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.actionUrl)))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}