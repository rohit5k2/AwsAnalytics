package rohit5k2.awsanalytics.ui.secure

import android.os.Bundle
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager
import rohit5k2.awsanalytics.R
import rohit5k2.awsanalytics.backend.handler.AwsAPIHandler
import rohit5k2.awsanalytics.backend.helper.AWSCommHandler
import rohit5k2.awsanalytics.ui.helper.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var ppm:PinpointManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun wireEvents() {

    }

    override fun init() {
        ppm = AWSCommHandler.getPinPointManager(this@MainActivity.applicationContext)
        ppm.sessionClient.startSession()
        logEvent()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        logout()
    }

    override fun onDestroy() {
        super.onDestroy()
        ppm.sessionClient.stopSession()
    }

    private fun logEvent(){
        var event = ppm.analyticsClient.createEvent("MainPage1")
            .withAttribute("loaded1", "true")
            .withMetric("load1", Math.random())

        ppm.analyticsClient.recordEvent(event)

        event = ppm.analyticsClient.createEvent("MainPage2")
            .withAttribute("loaded2", "true")
            .withMetric("load2", Math.random())

        ppm.analyticsClient.recordEvent(event)

        event = ppm.analyticsClient.createEvent("MainPage3")
            .withAttribute("loaded3", "true")
            .withMetric("load3", Math.random())

        ppm.analyticsClient.recordEvent(event)

        event = ppm.analyticsClient.createEvent("MainPage4")
            .withAttribute("loaded4", "true")
            .withMetric("load4", Math.random())

        ppm.analyticsClient.recordEvent(event)

        event = ppm.analyticsClient.createEvent("MainPage5")
            .withAttribute("loaded5", "true")
            .withMetric("load5", Math.random())

        ppm.analyticsClient.recordEvent(event)

    }

    private fun logout(){
        AwsAPIHandler.instance.logout()
    }
}
