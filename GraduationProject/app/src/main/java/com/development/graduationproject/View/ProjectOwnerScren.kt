package com.development.graduationproject.View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import com.development.graduationproject.Model.UserModel
import com.development.graduationproject.R
import com.development.graduationproject.Service.DBHelper
import com.development.graduationproject.databinding.ActivityProjectOwnerScrenBinding

class ProjectOwnerScren : AppCompatActivity() {
    private lateinit var binding: ActivityProjectOwnerScrenBinding
    private var bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectOwnerScrenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        val loginUser = intent.getStringExtra("loginUser")
        val loginSurname = intent.getStringExtra("loginSurname")
        val loginName = intent.getStringExtra("loginName")


        var ownerconfirmname = intent.getStringExtra("ownernameconfirm")
        var ownersurnameconfirm = intent.getStringExtra("ownersurnameconfirm")
        if (loginName == null && loginSurname == null && loginUser == null) {

            binding.textViewOwnerName.text = "$ownerconfirmname $ownersurnameconfirm"
        } else {
            binding.textViewOwnerName.text = "$loginName $loginSurname - $loginUser"
        }



        binding.buttonConfirm.setOnClickListener {
            val intent = Intent(this@ProjectOwnerScren, ConfirmActivity::class.java)
            bundle.putString("ownername", loginName)
            bundle.putString("ownersurname", loginSurname)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }



        binding.buttonExit.setOnClickListener {

            startActivity(Intent(this@ProjectOwnerScren, LoginActivity::class.java))
            finish()
        }
    }
}