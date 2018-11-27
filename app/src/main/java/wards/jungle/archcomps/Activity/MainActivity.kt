package wards.jungle.archcomps.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import wards.jungle.archcomps.Extensions.afterTextChanged
import wards.jungle.archcomps.ViewModel.MainActivity.LoginViewModel
import wards.jungle.archcomps.R

class MainActivity : AppCompatActivity() {

    private lateinit var submitBtn: Button
    private lateinit var emailEt: EditText
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        emailEt = email_et
        submitBtn = submit_btn
        emailEt.afterTextChanged {
            if(loginViewModel.validateEmail(it)) {
                emailEt.error = null
            } else {
                emailEt.error = "Enter valid email address"
            }
        }
    }
}

fun <T : Any> T.tag() = this::class.simpleName
