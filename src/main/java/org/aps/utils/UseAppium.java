package org.aps.utils;

import io.appium.java_client.AppiumDriver;
import net.serenitybdd.screenplay.Ability;

public class UseAppium implements Ability {
    private final AppiumDriver driver;

    public UseAppium(AppiumDriver driver) {
        this.driver = driver;
    }

    public static UseAppium with(AppiumDriver driver) {
        return new UseAppium(driver);
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}
