Feature: Datepicker - Selección de fecha y validaciones

  Background:
    Given que el usuario abre la página del datepicker

  Scenario: Selección de una fecha específica en un mes diferente al actual
    When el usuario abre el calendario del datepicker
    And el usuario navega hasta el mes "March" y año "2026"
    And el usuario selecciona el día "18"
    Then la fecha mostrada en el campo debe ser "18/03/2026"

