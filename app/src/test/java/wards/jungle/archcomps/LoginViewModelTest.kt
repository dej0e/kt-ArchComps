package wards.jungle.archcomps

import androidx.lifecycle.ViewModelProviders
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import wards.jungle.archcomps.Activity.MainActivity
import wards.jungle.archcomps.ViewModel.MainActivity.LoginViewModel
import java.lang.Exception


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = ArchApp::class)
class LoginViewModelTest {

    lateinit var mainActivity: MainActivity
    lateinit var loginViewModel: LoginViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mainActivity = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .resume()
                .get()

        loginViewModel = ViewModelProviders.of(mainActivity).get(LoginViewModel::class.java)
    }

    @Test
    fun testingIfEmailIdIsValid() {
        val mockedEmailId = "test@test.com"
        assertTrue(loginViewModel.validateEmail(mockedEmailId))
    }

    @Test
    fun testingIfEmailIdIsValidIfPatternMatchFails() {
        val mockedEmailId = "test-test.com"
        assertTrue(!loginViewModel.validateEmail(mockedEmailId))

    }

    @Test
    fun testingIfEmailIdIsValidIfEmptyStringIsPassed() {
        val mockedEmailId = ""
        assertTrue(!loginViewModel.validateEmail(mockedEmailId))
    }

    @After
    fun finishUp() {

    }
}
