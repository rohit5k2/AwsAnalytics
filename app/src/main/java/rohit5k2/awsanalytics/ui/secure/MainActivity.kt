package rohit5k2.awsanalytics.ui.secure

import android.os.Bundle
import rohit5k2.awsanalytics.R
import rohit5k2.awsanalytics.backend.handler.AwsAPIHandler
import rohit5k2.awsanalytics.backend.helper.AWSCommHandler
import rohit5k2.awsanalytics.ui.helper.BaseActivity
import com.amazonaws.mobileconnectors.pinpoint.analytics.monetization.AmazonMonetizationEventBuilder
import com.amazonaws.mobileconnectors.pinpoint.targeting.endpointProfile.EndpointProfileUser

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun wireEvents() {

    }

    override fun init() {
        ppm = AWSCommHandler.getPinPointManager(this@MainActivity.applicationContext)
        logEvent()
        //logMonetizationEvent()
        addCustomEndpointAttribute()
        assignUserIdToEndpoint()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        logout()
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
        ppm.analyticsClient.submitEvents()

    }

    private fun logMonetizationEvent() {
        val event = AmazonMonetizationEventBuilder.create(ppm.analyticsClient)
            .withCurrency("USD")
            .withItemPrice(6.99)
            .withProductId("PROD786BD236")
            .withQuantity(2.0)
            .withProductId("TRAN_56676886768F").build()
        ppm.analyticsClient.recordEvent(event)
        ppm.analyticsClient.submitEvents()
    }

    private fun addCustomEndpointAttribute() {
        val targetingClient = ppm.targetingClient
        val interests = listOf(/*"science", "politics", */"travel")
        targetingClient.addAttribute("interests", interests)
        targetingClient.updateEndpointProfile()
    }

    private fun assignUserIdToEndpoint() {
        val targetingClient = ppm.targetingClient
        val endpointProfile = targetingClient.currentEndpoint()
        val endpointProfileUser = EndpointProfileUser()
        endpointProfileUser.userId = AwsAPIHandler.instance.getIdentityId()
        endpointProfile.user = endpointProfileUser
        targetingClient.updateEndpointProfile(endpointProfile)
    }
}
