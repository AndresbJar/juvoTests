package org.aps.features.instalation.interactions;

import io.appium.java_client.AppiumDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.findby.By;
import org.aps.utils.UseAppium;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;


public class IngresarClave implements Interaction {
    private final String clave;

    public IngresarClave(String clave) {
        this.clave = clave;
    }

    public static IngresarClave conElCodigo(String clave) {
        return Tasks.instrumented(IngresarClave.class, clave);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        AppiumDriver driver = actor.abilityTo(UseAppium.class).getDriver();
        for (char digito : clave.toCharArray()) {
            String buttonName = convertirDigitoATexto(digito) + "_btn_pin";
            driver.findElement(By.id("com.juvomos.pos:id/" + buttonName)).click();
        }
    }

    private String convertirDigitoATexto(char digito) {
        switch (digito) {
            case '0': return "zero";
            case '1': return "one";
            case '2': return "two";
            case '3': return "three";
            case '4': return "four";
            case '5': return "five";
            case '6': return "six";
            case '7': return "seven";
            case '8': return "eight";
            case '9': return "nine";
            default: throw new IllegalArgumentException("Dígito no válido: " + digito);
        }
    }
}
