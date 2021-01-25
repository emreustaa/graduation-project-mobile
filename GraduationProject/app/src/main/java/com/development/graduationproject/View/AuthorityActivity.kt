package com.development.graduationproject.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.development.graduationproject.Adapter.ProjectAdapter
import com.development.graduationproject.R
import com.development.graduationproject.Service.ProjectsDB
import com.development.graduationproject.databinding.ActivityAuthorityBinding

class AuthorityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthorityBinding
    private var dbProject = ProjectsDB(this)
    private lateinit var projectAdapter: ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarProjects.title = "Projeler"
        binding.toolbarProjects.setTitleTextColor(resources.getColor(R.color.white))

        binding.rvProjects.layoutManager = LinearLayoutManager(applicationContext)
        projectAdapter = ProjectAdapter(dbProject.readData())
        binding.rvProjects.adapter = projectAdapter

    }
}