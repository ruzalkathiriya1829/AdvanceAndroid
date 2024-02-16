package com.example.firebaseprojectdemo.ModalClass

import java.lang.reflect.Constructor

class StudentModal {
    var StudId : String = ""
    var StudGRID: String =""
    var StudName: String =""
    var StudPhone: String =""
    var StudEmail: String =""
    var StudCourse: String =""
    var StudFees: String =""
    var rbMale: String =""
    var rbFemale: String =""
    var cbCooking: String =""
    var cbTraveling: String =""
    var cbSinging: String =""

    constructor(StudId: String,StudGRID: String,StudName: String,StudPhone: String,StudEmail: String,StudCourse: String,StudFees: String,rbMale: String,rbFemale: String,cbCooking: String,cbTraveling: String,cbSinging :String)
    {
        this.StudId = StudId
        this.StudGRID = StudGRID
        this.StudName = StudName
        this.StudPhone = StudPhone
        this.StudEmail = StudEmail
        this.StudCourse = StudCourse
        this.StudFees = StudFees
//        this.SelectedRb = SelectedRb
        this.rbMale = rbMale
        this.rbFemale = rbFemale
        this.cbCooking = cbCooking
        this.cbTraveling = cbTraveling
        this.cbSinging = cbSinging
    }

    constructor()
    {

    }
}
