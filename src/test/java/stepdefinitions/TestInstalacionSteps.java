package stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import org.aps.utils.AppiumDriverFactory;
import org.aps.utils.UseAppium;
import org.aps.questions.ActividadActual;
import org.aps.tasks.CompletarInstalacion;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

import java.net.MalformedURLException;

import io.appium.java_client.AppiumDriver;



public class TestInstalacionSteps {

    @Given("que el usuario abre la aplicacion")
public void que_el_usuario_abre_la_aplicacion() {
    try {
        AppiumDriver driver = AppiumDriverFactory.initializeDriver();
        OnStage.setTheStage(new Cast());
        OnStage.theActorCalled("Usuario").can(UseAppium.with(driver));
    } catch (MalformedURLException e) {
        throw new RuntimeException("Error al inicializar AppiumDriver: " + e.getMessage(), e);
    }
}

    @When("ingresa el codigo de instalacion {string}")
    public void ingresa_el_codigo_de_instalacion(String codigo) {
        Actor usuario = OnStage.theActorInTheSpotlight();
        usuario.attemptsTo(
            CompletarInstalacion.conElCodigo(codigo) 
        );
    }

    @Then("debe ser redirigido a la pantalla de autenticacion")
    public void debe_ser_redirigido_a_la_pantalla_de_autenticacion() {
        Actor usuario = OnStage.theActorInTheSpotlight();
        usuario.should(
            seeThat(ActividadActual.es(".features.authentication.AuthenticationActivity"))
        );
    }
}
