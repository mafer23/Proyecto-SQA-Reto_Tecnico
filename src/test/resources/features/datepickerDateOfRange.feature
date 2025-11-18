Feature: Datepicker - Selección de fecha simple

  Background:
    Given que el usuario abre la página del datepicker

  Scenario: Selección de una fecha y validación de formato
    When el usuario abre el calendario del datepicker
    And el usuario selecciona el día "15"
    Then la fecha mostrada en el campo debe ser "15/11/2025"
    And la fecha debe persistir en el campo con formato "dd/MM/yyyy"
