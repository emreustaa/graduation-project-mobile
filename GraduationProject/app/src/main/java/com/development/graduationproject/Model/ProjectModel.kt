package com.development.graduationproject.Model

data class ProjectModel(
    var id: Int = 0,
    var projectName: String = "",
    var detail: String = "",
    var dateTime: String = "",
    var requestAmount: String = "",
    var numberOfPeople: Int = 0,
    var category: String = "",
    var sector: String = "",
    var owner: String = "",
) {
}