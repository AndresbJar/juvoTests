package org.aps.features.instalation.interactions;

import io.appium.java_client.AppiumDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.findby.By;
import org.aps.utils.UseAppium;

public class ClickEnBotonLogin implements Interaction {

    public static ClickEnBotonLogin ahora() {
        return Tasks.instrumented(ClickEnBotonLogin.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        AppiumDriver driver = actor.abilityTo(UseAppium.class).getDriver();
        driver.findElement(By.id("com.juvomos.pos:id/check_pin")).click();
    }
}
