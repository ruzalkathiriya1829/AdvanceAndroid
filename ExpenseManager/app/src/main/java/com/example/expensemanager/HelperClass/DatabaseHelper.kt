package com.example.expensemanager.HelperClass

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.expensemanager.Model.DisplayModalClass
import com.example.expensemanager.Model.ModalClass
import com.example.expensemanager.Model.ModeModalclass

class DatabaseHelper(
    ragistrationActivity: Context,
    databasename: String,
    factory: Nothing?,
    version: Int
) : SQLiteOpenHelper(ragistrationActivity,databasename,factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {

        var table = "create table RegistrationTb(id Integer primary key autoincrement, name text, email text, username text, password text)"
        db?.execSQL(table)

        var tableCategory = "create table CategoryTb(id Integer primary key autoincrement, categoryName text)"
        db?.execSQL(tableCategory)

        var tablePayment = "create table PaymentTb(id Integer primary key autoincrement, PaymentMode text)"
        db?.execSQL(tablePayment)

        var tblIncomeExpense = "create table IncomeExpenseTb(id Integer primary key autoincrement ,amount Integer ,category text ,date text , mode text ,note text,type text)"
        db?.execSQL(tblIncomeExpense)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(name: String, email: String, username: String, password: String)
    {
        var db =writableDatabase
        var content: ContentValues = ContentValues()

        content.put("name", name)
        content.put("email", email)
        content.put("username", username)
        content.put("password", password)

        var result = db.insert("RegistrationTb",null,content)
        Log.e("TAG", "insertData: " + result )
    }


    fun insertCategory(categoryName: String)
    {
        var db = writableDatabase
        var content: ContentValues = ContentValues()

        content.put("categoryName", categoryName)

        var AddCategoryResult = db.insert("CategoryTb",null,content)
        Log.e("TAG", "insertCategory: "+ AddCategoryResult )

    }

    fun insertPaymentMode(PaymentMode: String)
    {
        var db = writableDatabase
        var content: ContentValues = ContentValues()

        content.put("PaymentMode",PaymentMode)

        var AddPaymentModeResult = db.insert("PaymentTb",null,content)
        Log.e("TAG", "insertPaymentMode: "+ AddPaymentModeResult )
    }

    //display category
    fun DisplayCategory(): ArrayList<ModalClass> {

        var list = ArrayList<ModalClass>()
        list.clear()

        var db = readableDatabase
        var query = "select * from CategoryTb"

        var cursor : Cursor = db.rawQuery(query,null)
        cursor.moveToFirst()

        do {
            var id = cursor.getString(0)
            var name = cursor.getString(1)
            Log.e("TAG", "DisplayCategory: " + name )
            list.add(ModalClass(id,name))
        }while (cursor.moveToNext())
        return list

    }

    fun DisplayMode(): ArrayList<ModeModalclass> {

        var mlist = ArrayList<ModeModalclass>()
        mlist.clear()

        var db = readableDatabase
        var query = "select * from PaymentTb"

        var cursor : Cursor = db.rawQuery(query,null)
        cursor.moveToFirst()

        do {
            var id = cursor.getString(0)
            var name = cursor.getString(1)

            Log.e("TAG", "DisplayMode: " + name )
            mlist.add(ModeModalclass(id,name))
        }while (cursor.moveToNext())

        return mlist
    }

    fun InsertIncomeData(
        amount: String,
        category: String,
        date: String,
        Mode: String,
        note: String
    )
    {
        var db = writableDatabase
        var content : ContentValues = ContentValues()

        content.put("amount",amount)
        content.put("category",category)
        content.put("date",date)
        content.put("Mode", Mode)
        content.put("note", note)
        content.put("type", "0")

         var result = db.insert("IncomeExpenseTb",null,content)

        Log.e("TAG", "InsertIncomeExpenseData: "+ result )
    }

    fun InsertExpenseData(amount: String, category: String, date: String, Mode: String, note: String)
    {
        var db = writableDatabase
        var content : ContentValues = ContentValues()

        content.put("amount",amount)
        content.put("category",category)
        content.put("date",date)
        content.put("Mode", Mode)
        content.put("note", note)
        content.put("type", "Expense")

        var result = db.insert("IncomeExpenseTb",null,content)

        Log.e("TAG", "InsertExpenseData: "+ result )
    }


    fun DisplayAllTransactionTableData() : ArrayList<DisplayModalClass>
    {
        var db = readableDatabase
        var Displaylist = ArrayList<DisplayModalClass>()
        var query = "select * from IncomeExpenseTb"

        var cursor : Cursor = db.rawQuery(query,null)
        cursor.moveToFirst()



        do {
//            var id = cursor.getString(0)
            var amount = cursor.getString(1)
            var category = cursor.getString(2)
            var date = cursor.getString(3)
            var mode = cursor.getString(4)
            var note = cursor.getString(5)
            var type = cursor.getString(6)

            Log.e("TAG", "DisplayTableData: " + category )

            Displaylist.add(DisplayModalClass(amount, category, date, mode, note, type))
        }while (cursor.moveToNext())

        return Displaylist

    }

    fun Delete(id : String )
    {
        var db = writableDatabase
        db .delete("IncomeExpenseTb ","id=?", arrayOf(id))
    }

}