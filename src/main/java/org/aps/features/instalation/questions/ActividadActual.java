package org.aps.features.instalation.questions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.aps.utils.UseAppium;

import java.util.logging.Logger;

public class ActividadActual implements Question<Boolean> {
    private final String expectedActivity;
    private static final Logger logger = Logger.getLogger(ActividadActual.class.getName());

    public ActividadActual(String expectedActivity) {
        this.expectedActivity = expectedActivity;
    }

    public static ActividadActual es(String expectedActivity) {
        return new ActividadActual(expectedActivity);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        AppiumDriver driver = actor.abilityTo(UseAppium.class).getDriver();

        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            String currentActivity;
            int attempts = 0;

            do {
                currentActivity = androidDriver.currentActivity();

                // Loguear la actividad actual
                logger.info("Intento #" + (attempts + 1) + ": Actividad actual - " + currentActivity);

                attempts++;
                if (currentActivity.equals(expectedActivity)) {
                    return true;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (attempts < 30);

            logger.info("Después de los intentos, la actividad actual es: " + currentActivity);
            return currentActivity.equals(expectedActivity);
        } else {
            throw new IllegalStateException("El driver no es un AndroidDriver y no puede obtener la actividad actual.");
        }
    }
}