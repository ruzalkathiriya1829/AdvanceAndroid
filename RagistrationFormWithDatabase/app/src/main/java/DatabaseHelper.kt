import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(
    mainActivity: Context,
    databasename: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(mainActivity,databasename,factory,version) {

    override fun onCreate(db: SQLiteDatabase?) {

        var table = "create table AdminDataTb(id Integer primary key autoincrement,name text,age text, address text, email text, mobileno text, course text)"
        db?.execSQL(table)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(name: String, age: String, address: String, email: String, mobileno: String, course: String)
    {
        var db = writableDatabase
        var content: ContentValues = ContentValues()

        content.put("name", name)
        content.put("age", age)
        content.put("address", address)
        content.put("email", email)
        content.put("mobileno", mobileno)
        content.put("course", course)

        var result = db.insert("AdminDataTb",null,content)
        Log.e("TAG", "insertData:" + result)
    }

    fun display(): ArrayList<DisplayModel> {
        var db = readableDatabase
        var query = "select * from AdminDataTb"

        var cursor : Cursor = db.rawQuery(query,null)

        var list = ArrayList<DisplayModel>()

        cursor.moveToFirst()

        do {

            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var age = cursor.getString(2)
            var address = cursor.getString(3)
            var email = cursor.getString(4)
            var mobileno = cursor.getString(5)
            var course = cursor.getString(6)

            list.add(DisplayModel(id,name,age,address,email,mobileno,course))

            Log.e("TAG", "id: " +id+ "name:" +name+ "age:" +age+ "address:" +address+ "email:" +email+ "mobileno:" +mobileno+ "course:" +course )

        }while (cursor.moveToNext())
        return list
    }
}