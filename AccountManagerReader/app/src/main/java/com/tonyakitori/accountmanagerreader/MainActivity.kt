package com.tonyakitori.accountmanagerreader

import android.accounts.Account
import android.accounts.AccountManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tonyakitori.accountmanagerreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var accountManager : AccountManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ctaGetAccount.setOnClickListener {
            getDataSaved()
        }
    }

    private fun getDataSaved() = with(binding){
        accountManager = AccountManager.get(this@MainActivity)
        val accountsByType = accountManager.getAccountsByType("tonyakitori.com")

        val accountFound = accountsByType.find { f -> f.name == "pruebadecuenta" }

        if(accountFound != null){
            val value = accountManager.getUserData(accountFound, "Key1")
            binding.dataSaved.text = value

            showMessage("Ahi tienes tus datos compa")
        }else{
            showMessage("No se encontro la cuenta")
        }
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}