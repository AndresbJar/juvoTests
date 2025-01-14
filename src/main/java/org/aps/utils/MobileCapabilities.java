package org.aps.utils;

import io.appium.java_client.android.options.UiAutomator2Options;
import java.time.Duration;


public class MobileCapabilities {

    public static UiAutomator2Options getCapabilities() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setPlatformVersion("15.0");
        options.setDeviceName("Pixel_Tablet_API_35");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.juvomos.pos");
        options.setAppActivity("com.juvomos.pos.features.home.HomeActivity");
        options.setNoReset(true);
        options.setAutoGrantPermissions(true);
        options.setNewCommandTimeout(Duration.ofSeconds(600));
        //options.setAutoLaunch(false);
        //options.setDontStopAppOnReset(true);

        return options;
    }
}
