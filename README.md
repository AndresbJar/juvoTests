# Documentación del Proyecto de Automatización de Pruebas

## 📦 Requisitos e Instalación

### Prerrequisitos:
- **Java 17**
- **Gradle 8.0+**
- **Appium Server 2.x**
- **Android SDK**
- **Dispositivo o emulador Android configurado**

### Configuración de Variables de Entorno:
- `ANDROID_HOME`: Ruta al SDK de Android.
- `JAVA_HOME`: Ruta al JDK.
- `PATH`: Incluir `$ANDROID_HOME/platform-tools`.

### Comandos de Instalación:
```bash
gradle clean build
gradle test
```

---

## 📦 Estructura del Proyecto

```
src/
└── main/
    └── java/
        └── org.aps/
            ├── features/
            │   ├── tips/
            │   │   ├── tasks/
            │   │   ├── interactions/
            │   │   ├── questions/
            │   │   └── userinterface/
            │   ├── multi_tender_payments/
            │   │   ├── tasks/
            │   │   ├── interactions/
            │   │   ├── questions/
            │   │   └── userinterface/
            │   ├── gift_cards/
            │   │   ├── tasks/
            │   │   ├── interactions/
            │   │   ├── questions/
            │   │   └── userinterface/
            │   └── utils/
            │       ├── CommonInteractions
            │       ├── CommonTasks
            │       ├── UIComponents
            │       └── DriverFactory
```

### Detalles:
- **Features**: Cada feature principal tiene un paquete dedicado con su lógica encapsulada en tasks, questions e interacciones.
- **Utils**:
  - `CommonTasks`: Tareas reutilizables como `NavigateTo`, `Login`, y `Logout`.
  - `CommonInteractions`: Interacciones genéricas como `ClickButton`, `EnterText`, y `SelectDropdown`.
  - `UIComponents`: Define elementos de UI compartidos, como botones o menús comunes.
  - `DriverFactory`: Configura el driver para Appium.

---

## 🎭 Screenplay Pattern

### 🎬 Actor
El actor es el ente principal que ejecuta las acciones:
```java
Actor usuario = OnStage.theActorCalled("Usuario");
```

### ✅ Tareas (Tasks)
Las tareas son combinaciones de múltiples interacciones:
```java
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
        actor.attemptsTo(IngresarClave.conElCodigo(clave));
    }
}
```

### 📦 Interacciones (Interactions)
Representan acciones atómicas como hacer clic o escribir texto:
```java
public class IngresarClave implements Interaction {
    private final String clave;

    public IngresarClave(String clave) {
        this.clave = clave;
    }

    public static IngresarClave conElCodigo(String clave) {
        return new IngresarClave(clave);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (char digito : clave.toCharArray()) {
            actor.attemptsTo(Click.on(InstalacionPage.obtenerBoton(digito)));
        }
    }
}
```

### 🎯 User Interface
Define la localización de elementos:
```java
public class InstalacionPage {
    public static final Target BOTON_LOGIN = Target.the("Botón de Login")
        .locatedBy("com.juvomos.pos:id/check_pin");

    public static Target obtenerBoton(char digito) {
        return Target.the("Botón de dígito " + digito)
            .locatedBy("com.juvomos.pos:id/" + digito + "_btn_pin");
    }
}
```

### ❓ Questions
Valida el estado de la aplicación:
```java
public class ActividadActual implements Question<Boolean> {
    private final String actividadEsperada;

    public ActividadActual(String actividadEsperada) {
        this.actividadEsperada = actividadEsperada;
    }

    public static ActividadActual es(String actividadEsperada) {
        return new ActividadActual(actividadEsperada);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        AppiumDriver driver = actor.abilityTo(UseAppium.class).getDriver();
        return driver.currentActivity().equals(actividadEsperada);
    }
}
```

---

## 🥒 Cucumber
Ejemplo de un escenario avanzado:
```gherkin
Feature: Pruebas Avanzadas de Login

  Scenario Outline: Validación de múltiples escenarios de login
    Given que el usuario abre la aplicación
    And ingresa el código de instalación "<codigo>"
    When presiona el botón de login
    Then debe ver el mensaje "<mensajeEsperado>"

    Examples:
      | codigo | mensajeEsperado         |
      | 123456 | Código válido ingresado |
      | 000000 | Código inválido         |
      |        | Código no ingresado     |
```

---

## 🚀 Ejecución con Gradle

- Para ejecutar las pruebas:
  ```bash
  gradle clean test
  ```

- Para ejecutar pruebas con un tag específico:
  ```bash
  gradle clean test -Dcucumber.filter.tags="@instalacion"
  ```

---

## 📊 Reportes con Serenity
Los reportes se generan automáticamente tras ejecutar las pruebas y están disponibles en:
```
app/target/site/serenity/index.html
