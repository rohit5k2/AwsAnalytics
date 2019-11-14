package rohit5k2.awsanalytics.model

/**
 * Created by Rohit on 8/1/2019:3:30 PM
 */
data class SignUpData(val email:String, val password:String, val attributes: HashMap<String, String>)