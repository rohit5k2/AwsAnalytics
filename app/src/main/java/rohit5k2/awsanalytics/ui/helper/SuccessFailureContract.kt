package rohit5k2.awsanalytics.ui.helper

/**
 * Created by Rohit on 7/31/2019:3:53 PM
 */
interface SuccessFailureContract<S, F> {
    fun successful(data: S)
    fun failed(data: F)
}