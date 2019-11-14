package rohit5k2.awsanalytics.utils

import android.util.Log

/**
 * Created by Rohit on 7/31/2019:4:52 PM
 */
object CLog {
    val TAG = "rohit5k2.awsanalytics"

    fun d(message:String){
        Log.d(TAG, message)
    }

    fun i(message:String){
        Log.i(TAG, message)
    }

    fun v(message:String){
        Log.v(TAG, message)
    }

    fun w(message:String){
        Log.w(TAG, message)
    }

    fun e(message:String){
        Log.e(TAG, message)
    }
}