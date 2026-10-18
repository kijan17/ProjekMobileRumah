package com.example.dzaki_3tia

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzaki_3tia.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Terapkan Toolbar sesuai instruksi tugas Pertemuan 6
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "CIVIKA"
            subtitle = "Pekanbaru Smart City"
        }

        // 2. Ambil username dari SharedPreferences untuk sapaan (Latihan Mandiri 5.1)
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "WARGA")
        binding.tvGreeting.text = "HALO, ${username?.uppercase()}!"

        // 3. Fungsi Dialog Logout sesuai Modul Pertemuan 6
        val showLogoutDialog = {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Hapus data login dari SharedPreferences
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()

                    // Arahkan kembali ke LoginActivity dan bersihkan tumpukan halaman
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // Aktifkan tombol Logout & tombol Profil kanan atas untuk memicu dialog Logout
        binding.btnLogout.setOnClickListener { showLogoutDialog() }
        binding.btnProfile.setOnClickListener { showLogoutDialog() }
    }
}