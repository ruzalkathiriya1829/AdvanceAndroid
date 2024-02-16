package com.example.quetesapp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import com.example.quetesapp.Model.LikeQuoteModel
import com.example.quetesapp.Model.displayModel
import com.example.quetesapp.Model.displayQuotesModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class MyDatabase(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    private val mDataBase: SQLiteDatabase? = null
    private var mNeedUpdate = false
    private val mContext: Context

    private fun copyDataBase() {
        if (!checkDataBase()) {
            this.readableDatabase
            close()
            try {
                copyDBFile()
            } catch (mIOException: IOException) {
                throw Error("ErrorCopyingDataBase")
            }
        }
    }

    private fun checkDataBase(): Boolean {
        val dbFile = File(DB_PATH + DB_NAME)
        return dbFile.exists()
    }

    //    TODO copy file
    @Throws(IOException::class)
    private fun copyDBFile() {
        val mInput = mContext.assets.open(DB_NAME)
        val mOutput: OutputStream = FileOutputStream(DB_PATH + DB_NAME)
        val mBuffer = ByteArray(1024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) mOutput.write(mBuffer, 0, mLength)
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) mNeedUpdate = true
    }

    //    TODO update database
    @Throws(IOException::class)
    fun updateDataBase() {
        if (mNeedUpdate) {
            val dbFile = File(DB_PATH + DB_NAME)
            if (dbFile.exists()) dbFile.delete()
            copyDataBase()
            mNeedUpdate = false
        }
    }

    @Synchronized
    override fun close() {
        mDataBase?.close()
        super.close()
    }


    fun DisplayData(): ArrayList<displayModel> {

        var db = readableDatabase
        var query = "select * from CategoryTb"
        var list = ArrayList<displayModel>()

        var cursor: Cursor = db.rawQuery(query, null)

        cursor.moveToFirst()

        do {
            var id = cursor.getInt(0)
            var categoryName = cursor.getString(1)

            list.add(displayModel(id, categoryName))

        } while (cursor.moveToNext())

        return list

    }

    fun DisplayQuotes(id: Int): ArrayList<displayQuotesModel>
    {
         //Log.e(TAG, "DisplayQuotes: " +id)

        var db = readableDatabase
        var query = "select * from QuotesTb where categoryId = $id"
        var quoteslist = ArrayList<displayQuotesModel>()

        var cursor: Cursor = db.rawQuery(query, null)

        cursor.moveToFirst()

        do {
            var id = cursor.getInt(0)
            var quotes = cursor.getString(1)
            var status = cursor.getInt(2)

            quoteslist.add(displayQuotesModel(id, quotes, status))
            Log.e(TAG, "DisplayQuotes: "+id)

        } while (cursor.moveToNext())

        return quoteslist

    }

    //update

    fun UpdateLikeQuotes(status: Int, id: Int)
    {
        var db = writableDatabase

       // Log.e("TAG", "UpdateLikeQuotes: "+status+"   id    "+id )

        var query = "UPDATE QuotesTb SET status=$status WHERE id = $id"
        db.execSQL(query)

    }

    //display fev quotes

    fun LikeQuoteDisplay(): ArrayList<LikeQuoteModel>
    {
        var read = readableDatabase
        var displayquote = ArrayList<LikeQuoteModel>()
        var query = "select * from  QuotesTb where status = 100"
        var cursor : Cursor = read.rawQuery(query,null)
        cursor.moveToFirst()
        do {
            var id = cursor.getInt(0)
            var Quotes = cursor.getString(1)
            var status = cursor.getInt(2)
            displayquote.add(LikeQuoteModel(id,Quotes,status))
        }while (cursor.moveToNext())
        return displayquote
    }



    companion object {
        private const val TAG = "MyDatabase"
        private const val DB_NAME = "QuotesDb.db"
        private var DB_PATH = ""
        private const val DB_VERSION = 1
    }

    init {
        if (Build.VERSION.SDK_INT >= 17) DB_PATH =
            context.applicationInfo.dataDir + "/databases/" else DB_PATH =
            "/data/data/" + context.packageName + "/databases/"
        mContext = context
        copyDataBase()
        this.readableDatabase
    }
}