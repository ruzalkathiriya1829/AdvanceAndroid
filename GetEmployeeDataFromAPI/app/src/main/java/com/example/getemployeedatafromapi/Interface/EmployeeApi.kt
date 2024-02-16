package com.example.getemployeedatafromapi.Interface

import com.example.getemployeedatafromapi.Model.EmployeeDetailsResponse
import com.example.getemployeedatafromapi.Model.EmployeeDetailsResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeApi {

    @GET("/employees")
    suspend fun getEmployeeDetail(): Response<EmployeeDetailsResponse>


}