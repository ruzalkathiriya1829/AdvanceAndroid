package com.example.expensemanager.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityHomeBinding
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

//    lateinit var AddIncomeLinear: LinearLayout
//    lateinit var drawerLayout: DrawerLayout
//    lateinit var navigationView: NavigationView
//    lateinit var imgCalendar: ImageView
//    lateinit var AddCategoryLinear: LinearLayout

    lateinit var binding: ActivityHomeBinding

    val calendar= Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //change Status bar color
        val window = this.window
        window.statusBarColor = this.resources.getColor(R.color.DarkBlue)

        initview()
    }


    private fun initview() {

//        AddIncomeLinear = findViewById(R.id.AddIncomeLinear)
//        drawerLayout = findViewById(R.id.drawerLayout)
//        navigationView = findViewById(R.id.navigationView)
//        imgCalendar = findViewById(R.id.imgCalendar)
//        AddCategoryLinear = findViewById(R.id.AddCategoryLinear)

        binding.Navigationimg.setOnClickListener {

            binding.drawerLayout.openDrawer(binding.navigationView)
        }


        binding.imgPremium.setOnClickListener {

            val i = Intent(this@HomeActivity, PreminumActivity::class.java)
            startActivity(i)
        }


        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.Home -> {
                    intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.AddCategory -> {
                    intent = Intent(this, AddCategoryActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.PaymentMode -> {
                    intent = Intent(this, AddPaymentActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.ExportedReport -> {
                    intent = Intent(this, ExportedReportsActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.Settings -> {
                    intent = Intent(this, SettingActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.Calendar -> {
                    intent = Intent(this, CalendarActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.Ratetheapp -> {
                    intent = Intent(this, RateActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.AppLock -> {
                    intent = Intent(this, AppLockActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }

        binding.drawerLayout.setOnClickListener {
            binding.drawerLayout.closeDrawer(binding.navigationView)
        }

        binding.imgCalendar.setOnClickListener {

            val i = Intent(this@HomeActivity, CalendarActivity::class.java)
            startActivity(i)
        }


        binding.AddIncomeLinear.setOnClickListener {

            val AddIncomeExpense = binding.txtAddIncome.text.toString()

            intent = Intent(this, AddIncomeExpenseActivity::class.java)
            intent.putExtra("AddIncomeExpense","0")
            startActivity(intent)
        }

        binding.AddExpenseLinear.setOnClickListener {

            val AddIncomeExpense = binding.txtAddExpense.text.toString()

            intent = Intent(this, AddIncomeExpenseActivity::class.java)
            intent.putExtra("AddIncomeExpense","1")
            startActivity(intent)
        }

        binding.AddCategoryLinear.setOnClickListener {
            intent = Intent(this, AddCategoryActivity::class.java)
            startActivity(intent)
        }

        binding.AllTransaction.setOnClickListener {
            val i = Intent(this, AllTransactionActivity::class.java)
            startActivity(i)
        }

        binding.ReportsLinear.setOnClickListener {
            intent = Intent(this, ReportsActivity::class.java)
            startActivity(intent)
        }

    }

}