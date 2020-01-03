package rohit5k2.awsanalytics.ui.helper

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import rohit5k2.awsanalytics.ui.open.LoginActivity
import rohit5k2.awsanalytics.ui.secure.MainActivity
import android.media.MediaSyncEvent.createEvent
import com.amazonaws.mobileconnectors.pinpoint.analytics.AnalyticsEvent
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager
import rohit5k2.awsanalytics.backend.handler.AwsAPIHandler
import rohit5k2.awsanalytics.backend.helper.AWSCommHandler


/**
 * Created by Rohit on 11/14/2019:2:57 PM
 */
abstract class BaseActivity:AppCompatActivity() {
    protected lateinit var ppm:PinpointManager

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        wireEvents()
        init()
    }

    abstract fun wireEvents()
    abstract fun init()

    protected fun showToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    protected fun goToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    protected fun goToMain(){
        startSession()
        logAuthenticationEvent("_userauth.sign_in")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    protected fun logAuthenticationEvent(type:String) {
        val event = ppm.analyticsClient.createEvent(type)
        ppm.analyticsClient.recordEvent(event)
        ppm.analyticsClient.submitEvents()
    }

    protected fun startSession(){
        ppm = AWSCommHandler.getPinPointManager(this@BaseActivity.applicationContext)
        ppm.sessionClient.startSession()
    }

    protected fun logout(){
        ppm.sessionClient.stopSession()
        AwsAPIHandler.instance.logout()
    }
}