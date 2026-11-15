package com.example.dzaki_3tia

data class UsulanModel(
    val judul: String,
    val deskripsi: String,
    val tanggal: String, // Kita gunakan untuk menampilkan label nomor/link
    val imageUrl: String,
    val actionUrl: String // Berisi format "tel:..." atau "https://..."
)