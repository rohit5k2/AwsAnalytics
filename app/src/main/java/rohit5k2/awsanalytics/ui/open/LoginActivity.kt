package rohit5k2.awsanalytics.ui.open

import android.content.Intent
import android.os.Bundle
import com.amazonaws.mobile.auth.core.internal.util.ThreadUtils
import com.amazonaws.mobile.client.results.SignInResult
import com.amazonaws.mobile.client.results.SignInState
import com.amazonaws.services.cognitoidentityprovider.model.UserNotConfirmedException
import kotlinx.android.synthetic.main.activity_login.*
import rohit5k2.awsanalytics.R
import rohit5k2.awsanalytics.backend.handler.AwsAPIHandler
import rohit5k2.awsanalytics.ui.helper.BaseActivity
import rohit5k2.awsanalytics.ui.helper.SuccessFailureContract
import rohit5k2.awsanalytics.utils.CLog

/**
 * Created by Rohit on 7/31/2019:4:25 PM
 */
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun wireEvents(){
        login.setOnClickListener {
            login()
        }
    }

    private fun login(){
        AwsAPIHandler.instance.login(login_emailid.text.toString(), login_password.text.toString(),
            object :SuccessFailureContract<SignInResult, Exception>{
                override fun successful(data: SignInResult) {
                    CLog.i("Sign in state is ${data.signInState}")
                    when {
                        data.signInState == SignInState.DONE -> goToMain()
                        data.signInState == SignInState.NEW_PASSWORD_REQUIRED -> failed(java.lang.Exception())
                        else -> failed(java.lang.Exception())
                    }
                }

                override fun failed(data: Exception) {
                    ThreadUtils.runOnUiThread {
                        showToast("Login failed.")
                    }
                }
            })
    }
}
