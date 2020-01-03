package rohit5k2.awsanalytics.ui.open

import android.os.Bundle
import android.widget.Toast
import com.amazonaws.mobile.client.UserState
import com.amazonaws.mobile.client.UserStateDetails
import rohit5k2.awsanalytics.R
import rohit5k2.awsanalytics.backend.helper.AWSCommHandler
import rohit5k2.awsanalytics.ui.helper.BaseActivity
import rohit5k2.awsanalytics.ui.helper.SuccessFailureContract
import rohit5k2.awsanalytics.utils.CLog

/**
 * Created by Rohit on 7/31/2019:2:23 PM
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_ativity)
    }

    override fun wireEvents() {
        // Nothing to be done here
    }

    override fun init() {
        initAwsComponents()
    }

    private fun initAwsComponents(){
        AWSCommHandler(this.applicationContext, object : SuccessFailureContract<UserStateDetails, String> {
            override fun successful(userStatusDetails: UserStateDetails) {
                runOnUiThread {
                    findUserStatusAndMove(userStatusDetails)
                }
            }

            override fun failed(reason: String) {
                runOnUiThread {
                    Toast.makeText(this@SplashActivity, reason, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun findUserStatusAndMove(userStateDetails: UserStateDetails){
        when(userStateDetails.userState){
            UserState.SIGNED_IN -> goToMain()
            UserState.SIGNED_OUT -> goToLogin()
            UserState.GUEST -> goToLogin()
        }

        CLog.i("Status is ${userStateDetails.userState.name}")
        finish()
    }
}
