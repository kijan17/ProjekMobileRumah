package com.example.dzaki_3tia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dzaki_3tia.databinding.FragmentCariBinding

class CariFragment : Fragment() {
    private var _binding: FragmentCariBinding? = null
    private val binding get() = _binding!!

    private val facilityList = listOf(
        FacilityModel("RSUD Arifin Achmad", "Jl. Diponegoro No.2, Pekanbaru", "24 Jam", "https://cdn-icons-png.flaticon.com/512/2966/2966327.png"),
        FacilityModel("Mal Pelayanan Publik (MPP)", "Jl. Jend. Sudirman No.464, Pekanbaru", "08.00-16.00", "https://cdn-icons-png.flaticon.com/512/1584/1584961.png"),
        FacilityModel("Polresta Pekanbaru", "Jl. Ahmad Yani No.11, Pekanbaru", "24 Jam", "https://cdn-icons-png.flaticon.com/512/2561/2561978.png"),
        FacilityModel("RS Awal Bros Sudirman", "Jl. Jend. Sudirman No.117, Pekanbaru", "24 Jam", "https://cdn-icons-png.flaticon.com/512/2966/2966327.png"),
        FacilityModel("Dinas Pemadam Kebakaran", "Jl. Cempaka No.31, Pekanbaru", "24 Jam", "https://cdn-icons-png.flaticon.com/512/785/785116.png"),
        FacilityModel("Bandara SSK II Pekanbaru", "Jl. Perhubungan, Simpang Tiga", "05.00-23.00", "https://cdn-icons-png.flaticon.com/512/3125/3125713.png"),
        FacilityModel("Terminal Bandar Raya Payung Sekaki", "Jl. Tuanku Tambusai Ujung", "24 Jam", "https://cdn-icons-png.flaticon.com/512/3448/3448339.png"),
        FacilityModel("Puskesmas Lima Puluh", "Jl. Sumber Sari, Sekip, Pekanbaru", "07.30-14.30", "https://cdn-icons-png.flaticon.com/512/2966/2966327.png"),
        FacilityModel("Disdukcapil Kota Pekanbaru", "Komplek MPP Jl. Jend. Sudirman", "08.00-15.30", "https://cdn-icons-png.flaticon.com/512/1584/1584961.png"),
        FacilityModel("Halte Trans Metro Sudirman", "Jl. Jend. Sudirman, Pusat Kota", "06.00-20.00", "https://cdn-icons-png.flaticon.com/512/3448/3448339.png")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCariBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Cari Fasilitas Kota"

        val adapter = FacilityAdapter(requireContext(), facilityList)
        binding.listFacilityItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}