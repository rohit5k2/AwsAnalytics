package rohit5k2.awsanalytics.backend.handler

import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.*
import rohit5k2.awsanalytics.backend.helper.AWSCommHandler
import rohit5k2.awsanalytics.model.SignUpData
import rohit5k2.awsanalytics.ui.helper.SuccessFailureContract
import java.lang.Exception

/**
 * Created by Rohit on 7/31/2019:7:22 PM
 */
class AwsAPIHandler {
    companion object {
        val instance:AwsAPIHandler = AwsAPIHandler()
    }

    //region authentication related API

    fun <S,F> login(username: String, password:String, listener:SuccessFailureContract<S, F>){
        AWSCommHandler.getMobileClient().signIn(username, password, null, object :Callback<SignInResult>{
            override fun onResult(result: SignInResult?) {
                listener.successful(result as S)
            }

            override fun onError(e: Exception?) {
                listener.failed(e as F)
            }
        })
    }

    fun logout(){
        AWSCommHandler.getMobileClient().signOut()
    }

    fun getUsername():String{
        return AWSCommHandler.getMobileClient().username
    }

    fun isSignedIn():String{
        return if(AWSCommHandler.getMobileClient().isSignedIn) "Logged In" else "Not logged In"
    }

    fun getIdentityId():String{
        return AWSCommHandler.getMobileClient().identityId
    }

    //endregion authentication related API
}