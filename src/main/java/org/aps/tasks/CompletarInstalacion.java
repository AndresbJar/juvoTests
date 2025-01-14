package org.aps.tasks;

import net.serenitybdd.screenplay.actions.Click;
import org.aps.interactions.ClickEnBotonLogin;
import org.aps.interactions.IngresarClave;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.aps.userinterface.InstalacionPage;

public class CompletarInstalacion implements Task {
    private final String clave;

    public CompletarInstalacion(String clave) {
        this.clave = clave;
    }

    public static CompletarInstalacion conElCodigo(String clave) {
        return Tasks.instrumented(CompletarInstalacion.class, clave);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                IngresarClave.conElCodigo(clave),
                ClickEnBotonLogin.ahora()
        );
    }
}