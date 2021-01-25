package com.development.graduationproject.View

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.development.graduationproject.Model.ProjectModel
import com.development.graduationproject.Service.DBHelper
import com.development.graduationproject.Service.ProjectsDB
import com.development.graduationproject.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity() {
    private var bundle = Bundle()
    private lateinit var binding: ActivityConfirmBinding
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerSector: Spinner
    private var category: String = ""
    private var sector: String = ""
    private var projectName: String = ""
    private var detail: String = ""
    private var dateTime: String = ""
    private var requestAmount: String = ""
    private var numberOfPeople: Int = 0
    private var owner: String = ""
    private lateinit var projectDBHelper: ProjectsDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ownername = intent.getStringExtra("ownername")
        val ownersurname = intent.getStringExtra("ownersurname")


        projectDBHelper = ProjectsDB(this)
        spinnerCategory = binding.spinnerCategory
        spinnerSector = binding.spinnerSector

        val spinnerCategoryList: MutableList<String> = mutableListOf("Ar-Ge", "Tasarım", "Yazılım")

        val adapterCategory =
            ArrayAdapter(this@ConfirmActivity, R.layout.simple_spinner_item, spinnerCategoryList)
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapterCategory

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                category = parent?.getItemAtPosition(position).toString()
                println("Category" + category)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        val spinnerSectorList: MutableList<String> =
            mutableListOf(
                "Medikal",
                "Bilişim",
                "Yazılım",
                "Enerji",
                "Eğitim",
                "Makine ve Teçhizat",
                "Mobilya"
            )


        val adapterSector =
            ArrayAdapter(this@ConfirmActivity, R.layout.simple_spinner_item, spinnerSectorList)
        adapterSector.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSector.adapter = adapterSector

        spinnerSector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sector = parent?.getItemAtPosition(position).toString()
                println("sector" + sector)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.buttonProjectSend.setOnClickListener {

            projectName = binding.editTextProjectTtile.text.toString()
            detail = binding.editTextProjectDetail.text.toString()
            dateTime = binding.editTextDateTime.text.toString()
            numberOfPeople = binding.editTextNumberOfPeople.text.toString().toInt()
            requestAmount = binding.editTextAmount.text.toString()
            owner = "$ownername $ownersurname"
            projectDBHelper.insertData(
                ProjectModel(
                    projectName = projectName,
                    detail = detail,
                    dateTime = dateTime,
                    requestAmount = requestAmount,
                    numberOfPeople = numberOfPeople,
                    category = category,
                    sector = sector,
                    owner = owner
                )
            )

            binding.editTextAmount.setText("")
            binding.editTextDateTime.setText("")
            binding.editTextNumberOfPeople.setText("")
            binding.editTextProjectTtile.setText("")
            binding.editTextProjectDetail.setText("")
            binding.spinnerCategory.setSelection(0)
            binding.spinnerSector.setSelection(0)

            val intent = Intent(this@ConfirmActivity, ProjectOwnerScren::class.java)
            bundle.putString("ownernameconfirm", ownername)
            bundle.putString("ownersurnameconfirm", ownersurname)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()

        }

    }

}