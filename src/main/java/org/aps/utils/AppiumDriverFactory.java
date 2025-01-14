package org.aps.utils;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverFactory {

    public static AndroidDriver initializeDriver() throws MalformedURLException {
        return new AndroidDriver(new URL("http://localhost:4723/wd/hub"), MobileCapabilities.getCapabilities());
    }
}
