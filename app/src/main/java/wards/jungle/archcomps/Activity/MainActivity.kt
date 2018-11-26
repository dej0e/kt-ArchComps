package wards.jungle.archcomps.Activity

import android.content.Intent
import android.net.Network
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import intent
import kotlinx.android.synthetic.main.activity_main.*
import wards.jungle.archcomps.Extensions.afterTextChanged
import wards.jungle.archcomps.ViewModel.MainActivity.UserViewModel
import wards.jungle.archcomps.R

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.simpleName
    }
    private lateinit var submitBtn: Button
    private lateinit var emailEt: EditText
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        emailEt = email_et
        submitBtn = submit_btn
        emailEt.afterTextChanged {
            if(userViewModel.validateEmail(it)) {
                emailEt.error = null
            } else {
                emailEt.error = "Enter valid email address"
            }
        }
    }
}

fun <T : Any> T.TAG() = this::class.simpleName
