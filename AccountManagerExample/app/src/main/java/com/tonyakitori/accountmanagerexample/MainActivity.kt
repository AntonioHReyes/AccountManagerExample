package com.tonyakitori.accountmanagerexample

import android.accounts.Account
import android.accounts.AccountManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import com.tonyakitori.accountmanagerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var accountManager : AccountManager
    private lateinit var currentAccount: Account

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        accountManager = AccountManager.get(this)

        binding.ctaCreateAccount.setOnClickListener{
            createAccount()
        }

        binding.ctaSaveData.setOnClickListener {
            saveDataInAccount()
        }

    }

    private fun createAccount() = with(binding){
        currentAccount = Account(
            "pruebadecuenta",
            "tonyakitori.com"
        )

        val addAccountResult = accountManager.addAccountExplicitly(
            currentAccount,
            "Password1234",
            bundleOf()
        )

        if(addAccountResult){
            showMessage("La cuenta fue creada con exito")
        }else{
            showMessage("La cuenta ya existe")
        }

        binding.ctaSaveData.isEnabled = true

    }

    private fun saveDataInAccount() {
        accountManager.setUserData(
            currentAccount,
            "Key1",
            "ValueRefreshToken"
        )

        showMessage("Data almacenada")
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}