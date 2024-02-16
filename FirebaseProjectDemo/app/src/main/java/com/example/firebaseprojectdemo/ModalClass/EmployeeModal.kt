package com.example.firebaseprojectdemo.ModalClass

import java.lang.reflect.Constructor

class EmployeeModal {
    var empId: String = ""
    var empName: String = ""
    var empPhone: String = ""
    var empEmail: String = ""
    var empAddress: String = ""
    var empSalary: String = ""

    constructor(empId: String, empName: String, empPhone: String, empEmail: String, empAddress: String, empSalary: String)
    {
        this.empId = empId
        this.empName = empName
        this.empPhone = empPhone
        this.empEmail = empEmail
        this.empAddress = empAddress
        this.empSalary = empSalary

    }

    constructor() {

    }
}
