package com.development.graduationproject.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.development.graduationproject.Adapter.ProjectAdapter
import com.development.graduationproject.R
import com.development.graduationproject.Service.ProjectsDB
import com.development.graduationproject.databinding.ActivityCommitteeBinding

class CommitteeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommitteeBinding
    private var dbProject = ProjectsDB(this)
    private lateinit var projectAdapter: ProjectAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommitteeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarProjectsCommittee.title = "Projeler"
        binding.toolbarProjectsCommittee.setTitleTextColor(resources.getColor(R.color.white))

        binding.rvCommitee.layoutManager = LinearLayoutManager(applicationContext)
        projectAdapter = ProjectAdapter(dbProject.readData())
        binding.rvCommitee.adapter = projectAdapter
    }
}