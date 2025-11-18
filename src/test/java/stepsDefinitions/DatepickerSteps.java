package stepsDefinitions;

import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import questions.FechaMostrada;
import questions.FormatoFechaValido;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DatepickerSteps {

    Actor actor = Actor.named("Usuario");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    WebDriver driver() {
        return ThucydidesWebDriverSupport.getDriver();
    }

    // -------------------- STEP COMMON --------------------
    @Given("que el usuario abre la página del datepicker")
    public void que_el_usuario_abre_la_pagina_del_datepicker() {

        actor.can(BrowseTheWeb.with(driver()));

        driver().get("https://jqueryui.com/datepicker/");
        driver().manage().window().maximize();
    }

    @When("el usuario abre el calendario del datepicker")
    public void el_usuario_abre_el_calendario_del_datepicker() {

        driver().switchTo().frame(0);
        driver().findElement(By.id("datepicker")).click();
    }

    // -------------------- STEP SELECCIÓN DE FECHA --------------------
    @When("el usuario navega hasta el mes {string} y año {string}")
    public void el_usuario_navega_hasta_el_mes_y_año(String mes, String año) {

        driver().switchTo().frame(0);


        WebElement campoFecha = driver().findElement(By.id("datepicker"));
        if (driver().findElements(By.className("ui-datepicker-calendar")).isEmpty()) {
            campoFecha.click();
        }


        while (true) {
            String displayedMonth = driver().findElement(By.className("ui-datepicker-month")).getText();
            String displayedYear  = driver().findElement(By.className("ui-datepicker-year")).getText();

            if (displayedMonth.equals(mes) && displayedYear.equals(año)) break;

            driver().findElement(By.className("ui-datepicker-next")).click();
        }
    }

    @When("el usuario selecciona el día {string}")
    public void el_usuario_selecciona_el_dia(String dia) {


        driver().switchTo().frame(0);


        if (driver().findElements(By.className("ui-datepicker-calendar")).isEmpty()) {
            driver().findElement(By.id("datepicker")).click();
        }


        WebElement dayElement = driver().findElement(By.xpath("//a[text()='" + dia + "']"));
        dayElement.click();
    }

    // -------------------- VALIDACIÓN CON QUESTIONS --------------------
    @Then("la fecha mostrada en el campo debe ser {string}")
    public void la_fecha_mostrada_en_el_campo_debe_ser(String fechaEsperada) {


        String fechaObtenida = FechaMostrada.enElCampo().answeredBy(actor);


        DateTimeFormatter formatoEntrada;
        if (fechaObtenida.contains("/")) {

            formatoEntrada = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        } else {

            formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }


        DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaConvertida = LocalDate.parse(fechaObtenida, formatoEntrada).format(formatoSalida);


        actor.attemptsTo(
                Ensure.that(fechaConvertida).isEqualTo(fechaEsperada)
        );
    }


    @Then("la fecha debe persistir en formato válido")
    public void la_fecha_debe_persistir_en_formato_valido() {

        actor.attemptsTo(
                Ensure.that(FormatoFechaValido.esCorrecto()).isTrue()
        );
    }

    // Validar Fecha


    @When("el usuario selecciona el día {string} en mes {string} y año {string}")
    public void el_usuario_selecciona_el_día_en_mes_y_año(String dia, String mes, String año) {

        driver().switchTo().frame(0);

        // Asegurar que el calendario esté abierto
        WebElement campoFecha = driver().findElement(By.id("datepicker"));
        if (driver().findElements(By.className("ui-datepicker-calendar")).isEmpty()) {
            campoFecha.click();
        }


        WebElement dayElement = driver().findElement(By.xpath("//a[text()='" + dia + "']"));
        dayElement.click();

        driver().switchTo().defaultContent(); // volver al main
    }

    @Then("la fecha debe persistir en el campo con formato {string}")
    public void la_fecha_debe_persistir_en_el_campo_con_formato(String formato) {
        actor.attemptsTo(
                Ensure.that(FormatoFechaValido.esCorrecto()).isTrue()
        );
    }


}
