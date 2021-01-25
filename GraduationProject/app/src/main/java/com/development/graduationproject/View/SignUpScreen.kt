package com.development.graduationproject.View

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.development.graduationproject.Model.UserModel
import com.development.graduationproject.Service.DBHelper
import com.development.graduationproject.databinding.ActivitySignUpScreenBinding

class SignUpScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpScreenBinding
    private lateinit var dbHelper: DBHelper
    private lateinit var spinnerUserType: Spinner
    private var name: String = ""
    private var surname: String = ""
    private var email: String = ""
    private var password: String = ""
    private var username: String = ""
    private var userType: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)
        spinnerUserType = binding.spinnerUserType
        val spinnerList: MutableList<String> =
            mutableListOf("Proje Sahibi", "TTO Yetkilisi", "Yetkili Hakem")
        val adapter = ArrayAdapter(this@SignUpScreen, R.layout.simple_spinner_item, spinnerList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUserType.adapter = adapter


        spinnerUserType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                userType = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.buttonSave.setOnClickListener {
            name = binding.etName.text.toString()
            surname = binding.etSurname.text.toString()
            email = binding.etMail.text.toString()
            password = binding.etPassword.text.toString()
            username = binding.etUsername.text.toString()
            dbHelper.insertData(
                UserModel(
                    name = name,
                    surname = surname,
                    username = username,
                    mail = email,
                    password = password,
                    userType = userType
                )
            )
            startActivity(Intent(this@SignUpScreen, LoginActivity::class.java))
        }
        binding.buttonDelete.setOnClickListener {
            binding.etName.setText("")
            binding.etSurname.setText("")
            binding.etMail.setText("")
            binding.etPassword.setText("")
            binding.etUsername.setText("")
            binding.spinnerUserType.setSelection(0)
        }

    }
}