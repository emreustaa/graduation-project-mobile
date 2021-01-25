package com.development.graduationproject.View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.development.graduationproject.Service.DBHelper
import com.development.graduationproject.databinding.ActivityLoginBinding
import java.security.acl.Owner

class LoginActivity : AppCompatActivity() {
    private val PROJE_SAHIBI = "Proje Sahibi"
    private val TTO_YETKILISI = "TTO Yetkilisi"
    private val YETKILI_HAKEM = "Yetkili Hakem"
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userName: String
    private lateinit var password: String
    private lateinit var dbHelper: DBHelper
    private var isSuccess: Boolean = false
    private lateinit var loginUser: String
    private lateinit var loginName: String
    private lateinit var loginSurname: String
    private var bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.buttonLogin.setOnClickListener {

            userName = binding.editTextUsername.text.toString()
            password = binding.editTextPassword.text.toString()

            for (users in dbHelper.readData()) {

                if (userName == users.username && password == users.password) {
                    isSuccess = true
                    loginUser = users.userType
                    loginName = users.name
                    loginSurname = users.surname
                }


            }
            println("Is success" + isSuccess)
            if (isSuccess) {
                binding.editTextPassword.setText("")
                binding.editTextUsername.setText("")
                if (loginUser == PROJE_SAHIBI) {
                    val intent = Intent(this@LoginActivity, ProjectOwnerScren::class.java)
                    bundle.putString("username", userName)
                    bundle.putString("loginUser", loginUser)
                    bundle.putString("loginName", loginName)
                    bundle.putString("loginSurname", loginSurname)
                    intent.putExtras(bundle)
                    startActivity(intent)

                } else if (loginUser == TTO_YETKILISI) {
                    val intent = Intent(this@LoginActivity, AuthorityActivity::class.java)
                    bundle.putString("username", userName)
                    bundle.putString("loginUser", loginUser)
                    bundle.putString("loginName", loginName)
                    bundle.putString("loginSurname", loginSurname)
                    intent.putExtras(bundle)
                    startActivity(intent)
                } else if (loginUser == YETKILI_HAKEM) {
                    val intent = Intent(this@LoginActivity, CommitteeActivity::class.java)
                    bundle.putString("username", userName)
                    bundle.putString("loginUser", loginUser)
                    bundle.putString("loginName", loginName)
                    bundle.putString("loginSurname", loginSurname)
                    intent.putExtras(bundle)
                    startActivity(intent)
                } else {

                }

            } else {
                Toast.makeText(this, "Hatalı Giriş Yaptınız..!", Toast.LENGTH_LONG).show()
            }


        }
        binding.buttonSignUp.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    SignUpScreen::class.java
                )
            )
        }
    }
}