package wards.jungle.archcomps.Repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import checkForConnectivity
import wards.jungle.archcomps.ArchApp
import wards.jungle.archcomps.Dao.UserDao
import wards.jungle.archcomps.Database.ArchCompsDatabase
import wards.jungle.archcomps.Model.User

class LoginRepository(application: Application) {
    private var userDao: UserDao
    val allUsers: LiveData<List<User>>
        get() = userDao.getAllUsers()


    init {
        val archCompsDb: ArchCompsDatabase? = ArchCompsDatabase.getInstance(application)
        this.userDao = archCompsDb!!.notesDao()
    }

    fun insert(user: User) {
        if(ArchApp.appContext.checkForConnectivity()) {

        } else {
            DoAsyncWithArgs<UserDao, User>(userDao) { dao, item ->
                dao.insert(item)
            }.execute(user)
        }

    }

    fun update(user: User) {
        if(ArchApp.appContext.checkForConnectivity()) {

        } else {
            DoAsyncWithArgs<UserDao, User>(userDao) { dao, item ->
                dao.update(item)
            }.execute(user)
        }

    }
    fun delete(user: User) {
        if(ArchApp.appContext.checkForConnectivity()) {

        } else {
            DoAsyncWithArgs<UserDao, User>(userDao) { dao, item ->
                dao.delete(item)
            }.execute(user)
        }
    }


    fun deleteAllUsers() {
        if(ArchApp.appContext.checkForConnectivity()) {

        } else {
            DoAsync(userDao) { dao ->
                dao.deleteAllUsers()
            }.execute()
        }
    }
}


class DoAsync<T>(private val dao: T, val handler: (T) -> Unit) : AsyncTask<Void, Void, Int>() {
    override fun doInBackground(vararg params: Void?): Int {
        handler(dao)
        return 1
    }
}

class DoAsyncWithArgs<T, P>(private val dao: T, val handler: (T, P) -> Unit) : AsyncTask<P, Void, Int>() {
    override fun doInBackground(vararg params: P): Int {
        handler(dao, params[0])
        return 1
    }
}