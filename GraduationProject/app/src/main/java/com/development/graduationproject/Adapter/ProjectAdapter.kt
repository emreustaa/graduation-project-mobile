package com.development.graduationproject.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.development.graduationproject.Model.ProjectModel
import com.development.graduationproject.R

class ProjectAdapter(val projectList: MutableList<ProjectModel>) :
    RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    inner class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textViewProjectName: TextView
        var textViewProjectDetail: TextView
        var textViewProjectOwner: TextView
        var textViewProjectCategory: TextView
        var textViewProjectSector: TextView
        var textViewProjectAmount: TextView
        var textViewProjectPeople: TextView
        var textViewDateTime: TextView

        init {
            textViewProjectAmount = view.findViewById(R.id.textViewProjectAmount)
            textViewProjectPeople = view.findViewById(R.id.textViewProjectPeople)
            textViewProjectSector = view.findViewById(R.id.textViewProjectSector)
            textViewProjectCategory = view.findViewById(R.id.textViewProjectCategory)
            textViewProjectOwner = view.findViewById(R.id.textViewProjectOwner)
            textViewProjectDetail = view.findViewById(R.id.textViewProjectDetail)
            textViewProjectName = view.findViewById(R.id.textViewProjectName)
            textViewDateTime = view.findViewById(R.id.textViewDateTime)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_projects, parent, false)
        return ProjectViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {

        holder.textViewProjectAmount.text = "Talep Edilen Tutar: "+projectList[position].requestAmount
        holder.textViewProjectPeople.text = "Kişi Sayısı: "+projectList[position].numberOfPeople.toString()
        holder.textViewProjectSector.text = "Sektör: "+projectList[position].sector
        holder.textViewProjectCategory.text = "Kategori: "+projectList[position].category
        holder.textViewProjectOwner.text = "Proje Sahibi: "+projectList[position].owner
        holder.textViewProjectDetail.text = "Proje Detayı: "+projectList[position].detail
        holder.textViewProjectName.text = "Proje Adı: "+projectList[position].projectName
        holder.textViewDateTime.text = "Süre (Ay): "+projectList[position].dateTime
    }

    override fun getItemCount(): Int {
        return projectList.size
    }
}