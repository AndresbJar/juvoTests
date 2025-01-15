package org.aps.features.instalation.tasks;

import org.aps.features.instalation.interactions.ClickEnBotonLogin;
import org.aps.features.instalation.interactions.IngresarClave;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

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