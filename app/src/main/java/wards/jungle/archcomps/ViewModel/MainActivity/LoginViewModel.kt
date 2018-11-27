package wards.jungle.archcomps.ViewModel.MainActivity

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import wards.jungle.archcomps.Model.User
import wards.jungle.archcomps.Repository.LoginRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private var loginRepository: LoginRepository = LoginRepository(application)
    var allUsers: LiveData<List<User>> = loginRepository.allUsers
    var name: MutableLiveData<String> = MutableLiveData<String>()
    fun insert(user: User) {
        loginRepository.insert(user)
    }

    fun update(user: User) {
        loginRepository.update(user)
    }

    fun delete(user: User) {
        loginRepository.delete(user)
    }

    fun deleteAllUsers() {
        loginRepository.deleteAllUsers()
    }

    fun validateEmail(emailId: String): Boolean {
        return emailId.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailId).matches()
    }
}