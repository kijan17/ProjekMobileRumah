package com.example.dzaki_3tia

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}


























/* ================= PANDUAN COPY-PASTE UTS DARI PROJEK RUMAH =================

1. SETUP AWAL & RESOURCE
   - build.gradle.kts (Module :app):
     [SALIN] blok buildFeatures { viewBinding = true } dari build.gradle.kts rumah -> klik Sync Now.
   - themes.xml: Pastikan ujung parent pakai "NoActionBar".
   - colors.xml & strings.xml: Tambahkan 4 warna soal & teks deskripsi/judul kartu.
   - drawable: Klik kanan drawable -> New -> Vector Asset (buat 1 ikon splash + 3 ikon menu).

2. SPLASH SCREEN
   - Buat Activity baru: SplashScreenActivity.
   - activity_splash_screen.xml:
     [SALIN] isi activity_spalsh_screen.xml dari projek rumah.
     [UBAH] android:background jadi warna gelap soal, dan src ImageView jadi ikon splash baru.
   - SplashScreenActivity.kt:
     [SALIN] isi SpalshScreenActivity.kt dari projek rumah (bagian lifecycleScope.launch).
     [UBAH] Tujuan Intent pastikan ke MainActivity::class.java:
     lifecycleScope.launch {
         delay(2000)
         startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
         finish()
     }
   - AndroidManifest.xml:
     [UBAH] Pindahkan blok <intent-filter> (MAIN & LAUNCHER) dari MainActivity ke SplashScreenActivity, dan ubah android:exported="true".

3. BOTTOM NAVIGATION & MAIN ACTIVITY
   - Buat folder & file res/menu/bottom_nav_menu.xml:
     [SALIN] isi bottom_nav_menu.xml dari projek rumah.
     [UBAH] id, icon, dan title ketiga item sesuai soal (misal: nav_menu, nav_promo, nav_tentang).
   - activity_main.xml:
     [SALIN] semua isi activity_home.xml dari projek rumah (FrameLayout + BottomNavigationView).
   - Buat 3 Fragment Blank (misal: MenuFragment, PromoFragment, TentangFragment).
   - MainActivity.kt:
     [SALIN] isi HomeActivity.kt dari projek rumah (binding, replaceFragment, setOnItemSelectedListener).
     [UBAH] Tipe binding di atas jadi ActivityMainBinding.
     [UBAH] Di dalam when(it.itemId), sesuaikan R.id.nav_xxx dan nama Fragment yang dipanggil.
     *KHUSUS PAKET B (Jika diminta Tab Tengah tampil duluan saat buka aplikasi):
     replaceFragment(RingkasanFragment())
     binding.bottomNavView.selectedItemId = R.id.nav_ringkasan

4. HALAMAN LIST / CUSTOM ADAPTER (Tab Menu / Tab Studio)
   - Buat Data Class (misal: KopiModel.kt):
     [SALIN] FacilityModel.kt dari projek rumah.
     [UBAH] Nama class dan ganti ke 5 variabel String sesuai tabel soal (nama, jenis, suhu, deskripsi, tarif).
   - Buat layout item_kopi.xml:
     [SALIN] item_facility.xml dari projek rumah.
     [UBAH] HAPUS ImageView. Sisakan/buat 4 TextView berurutan ke bawah (textNama, textJenisUsaha, textDeskripsi, textTarif).
   - Buat Adapter (misal: KopikoAdapter.kt):
     [SALIN] FacilityAdapter.kt dari projek rumah.
     [UBAH] 1. HAPUS baris import milik projek lama (com.example.dzaki_3tia...) & import Glide.
     [UBAH] 2. Ganti FacilityModel menjadi KopiModel.
     [UBAH] 3. Ganti ItemFacilityBinding menjadi ItemKopiBinding (sesuai nama item_kopi.xml).
     [UBAH] 4. HAPUS kode Glide. Pasang teks & klik di dalam getView():
     override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
         val binding = ItemKopiBinding.inflate(LayoutInflater.from(context), parent, false)
         val view = binding.root
         val data = facilities[position] // pastikan nama variabel list di atas adalah 'facilities'

         binding.textNama.text = data.nama
         binding.textJenisUsaha.text = "${data.jenis} • ${data.suhu}"
         binding.textDeskripsi.text = data.deskripsi
         binding.textTarif.text = data.tarif

         // Klik Biasa -> Toast (Dipakai di Paket F & B)
         view.setOnClickListener {
             Toast.makeText(context, "${data.nama} - ${data.tarif}", Toast.LENGTH_SHORT).show()
         }
         // Tekan Lama -> Snackbar BATAL (Khusus Paket B, hapus jika Paket F)
         view.setOnLongClickListener {
             Snackbar.make(parent, "${data.nama} dipilih", Snackbar.LENGTH_LONG)
                 .setAction("BATAL") {
                     Toast.makeText(context, "Pilihan dibatalkan", Toast.LENGTH_SHORT).show()
                 }.show()
             true
         }
         return view
     }
   - fragment_menu.xml:
     [SALIN] semua isi fragment_cari.xml dari projek rumah (CoordinatorLayout, Toolbar, ListView).
     [UBAH] warna background & warna toolbar sesuai soal. Biarkan ID ListView tetap @+id/listFacilityItems biar ga repot.
   - MenuFragment.kt:
     [SALIN] semua isi CariFragment.kt dari projek rumah.
     [UBAH] 1. Ganti FragmentCariBinding menjadi FragmentMenuBinding.
     [UBAH] 2. Isi datalist dengan 5 data dari tabel soal.
     [UBAH] 3. Di onViewCreated, panggil adapter baru:
     val adapter = KopikoAdapter(requireContext(), datalist)
     binding.listFacilityItems.adapter = adapter

5. HALAMAN CARDVIEW (Promo / Ringkasan / Tentang / Informasi)
   - XML (fragment_promo.xml & fragment_tentang.xml):
     [UBAH] HAPUS dulu TextView bawaan "Hello blank fragment", ganti root paling atas jadi ConstraintLayout.
     [SALIN] blok <androidx.cardview.widget.CardView ...> dari fragment_home.xml projek rumah.
     [UBAH] Atur CardView 1: width="0dp", height="wrap_content", tambah constraint Top_toTopOf="parent", Start_toStartOf="parent", End_toEndOf="parent".
     [UBAH] Atur CardView 2 (di bawahnya): sama dengan CardView 1, tapi Top-nya pakai app:layout_constraintTop_toBottomOf="@id/cardPromo1".
     [UBAH] Di dalam LinearLayout milik CardView, pastikan android:orientation="vertical", isi dengan TextView judul, TextView deskripsi, dan MaterialButton.

   - Kotlin (PromoFragment.kt & TentangFragment.kt):
     [SALIN] kerangka binding (onCreateView & onDestroyView) dari HomeFragment.kt projek rumah.
     [UBAH] Sesuaikan nama FragmentPromoBinding / FragmentTentangBinding.
     [TAMBAH] Di dalam onViewCreated, masukkan aksi tombol sesuai permintaan soal:

     // A) Jika tombol diminta munculkan Snackbar + aksi BATAL (Halaman Promo Paket F):
     binding.btnPakai1.setOnClickListener {
         Snackbar.make(binding.root, "Promo berhasil dipakai", Snackbar.LENGTH_LONG)
             .setAction("BATAL") {
                 Toast.makeText(requireContext(), "Promo dibatalkan", Toast.LENGTH_SHORT).show()
             }.show()
     }

     // B) Jika tombol diminta pindah ke tab lain (Halaman Ringkasan Paket B):
     binding.btnLihatDaftar.setOnClickListener {
         val bottomNav = requireActivity().findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_nav_view)
         bottomNav.selectedItemId = R.id.nav_studio // sesuaikan ID menu tab tujuan
     }

     // C) Jika tombol hanya diminta munculkan Toast / Snackbar biasa (Tentang / Informasi):
     binding.btnJamBuka.setOnClickListener {
         Toast.makeText(requireContext(), "Buka setiap hari: 08.00 - 22.00 WIB", Toast.LENGTH_SHORT).show()
         // Atau jika diminta Snackbar:
         // Snackbar.make(binding.root, "Admin akan menghubungi Anda", Snackbar.LENGTH_SHORT).show()
     }

6. SEBELUM KUMPUL ZIP:
   - Jalankan aplikasi, screenshot semua halaman & saat Toast/Snackbar muncul!
   - Tutup Android Studio -> Buka File Explorer -> Hapus folder: build, app/build, .gradle, dan .idea -> Kompres jadi ZIP.
=============================================================================== */