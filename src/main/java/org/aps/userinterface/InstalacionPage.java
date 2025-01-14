package org.aps.userinterface;

import net.serenitybdd.screenplay.targets.Target;

public class InstalacionPage {

    public static Target obtenerBotonPorNombre(String buttonName) {
        return Target.the("Botón " + buttonName)
                .locatedBy("com.juvomos.pos:id/" + buttonName);
    }

    public static final Target BOTON_LOGIN = Target.the("Botón de Login")
            .locatedBy("com.juvomos.pos:id/checkBigImage");
}