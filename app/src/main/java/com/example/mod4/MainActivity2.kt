package com.example.mod4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var etPanjang: EditText
    private lateinit var etLebar: EditText
    private lateinit var etTinggi: EditText
    private lateinit var btnHitung: Button
    private lateinit var tvHasil: TextView

    //savestate supaya waktu dirotate ga hilang hasilnya
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        etPanjang = findViewById(R.id.et_panjang)
        etLebar = findViewById(R.id.et_lebar)
        etTinggi = findViewById(R.id.et_tinggi)
        tvHasil = findViewById(R.id.tv_hasil)
        btnHitung = findViewById(R.id.btn_hitung)
        btnHitung.setOnClickListener(this)

        if (savedInstanceState != null) {
            val hasil = savedInstanceState.getString(STATE_RESULT)
            tvHasil.text = hasil
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvHasil.text.toString())
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_hitung) {
            val inputPanjang = etPanjang.text.toString()
                .trim() //trim untuk hapus spasi in case pengguna salah ketik
            val inputLebar = etLebar.text.toString().trim()
            val inputTinggi = etTinggi.text.toString().trim()

            var isEmptyFields = false
            if (inputPanjang.isEmpty()) {
                isEmptyFields = true
                etPanjang.error = "Panjang Tidak Boleh Kosong"
            }
            if (inputLebar.isEmpty()) {
                isEmptyFields = true
                etLebar.error = "Lebar Tidak Boleh Kosong"
            }
            if (inputTinggi.isEmpty()) {
                isEmptyFields = true
                etTinggi.error = "Tinggi Tidak Boleh Kosong"
            }
            if (!isEmptyFields) {
                val volume =
                    inputPanjang.toDouble() * inputLebar.toDouble() * inputTinggi.toDouble()
                tvHasil.text = volume.toString()
            }
        }
    }
}