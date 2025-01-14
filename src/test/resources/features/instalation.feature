Feature: Instalación de la app

  @instalacion
  Scenario: Ingreso de codigo válido
    Given que el usuario abre la aplicacion
    When ingresa el codigo de instalacion "620650"
    Then debe ser redirigido a la pantalla de autenticacion
