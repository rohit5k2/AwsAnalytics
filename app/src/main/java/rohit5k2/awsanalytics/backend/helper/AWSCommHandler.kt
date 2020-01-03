package rohit5k2.awsanalytics.backend.helper

import android.content.Context
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobile.config.AWSConfiguration
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager
import rohit5k2.awsanalytics.ui.helper.SuccessFailureContract

/**
 * Created by Rohit on 7/31/2019:2:19 PM
 */
class AWSCommHandler <S, F>(context: Context, setupCallback: SuccessFailureContract<S, F>) {
    companion object {
        fun getMobileClient():AWSMobileClient{
            return AWSMobileClient.getInstance()
        }

        fun getPinPointManager(context: Context):PinpointManager{
            val pinPointConfig = PinpointConfiguration(context, getMobileClient(), AWSConfiguration(context))
            return PinpointManager(pinPointConfig)
        }
    }

    init{
        initMobileClient(context, setupCallback)
    }

    private fun initMobileClient(context: Context, setupCallback: SuccessFailureContract<S, F>){
        val awsConfig = AWSConfiguration(context)
        AWSMobileClient.getInstance().initialize(context.applicationContext, awsConfig, object : Callback<UserStateDetails> {
            override fun onResult(userStateDetails: UserStateDetails) {
                setupCallback.successful(userStateDetails as S)
            }

            override fun onError(e: Exception) {
                setupCallback.failed(e.message as F)
            }
        })
    }
}