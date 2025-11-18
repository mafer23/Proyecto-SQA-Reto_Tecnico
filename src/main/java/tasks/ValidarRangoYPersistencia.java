package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarRangoYPersistencia implements Task {

    private final String fechaMin;
    private final String fechaMax;
    private final String dia;
    private final String mes;
    private final String año;

    private final Target DATEPICKER_FIELD = Target.the("datepicker").located(By.id("datepicker"));
    private final Target NEXT_BUTTON     = Target.the("botón siguiente").located(By.className("ui-datepicker-next"));
    private final Target MONTH_LABEL     = Target.the("mes visible").located(By.className("ui-datepicker-month"));
    private final Target YEAR_LABEL      = Target.the("año visible").located(By.className("ui-datepicker-year"));

    public ValidarRangoYPersistencia(String fechaMin, String fechaMax, String dia, String mes, String año) {
        this.fechaMin = fechaMin;
        this.fechaMax = fechaMax;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Asignar min y max al input del datepicker
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('min', arguments[1])",
                DATEPICKER_FIELD.resolveFor(actor), fechaMin);

        js.executeScript("arguments[0].setAttribute('max', arguments[1])",
                DATEPICKER_FIELD.resolveFor(actor), fechaMax);

        // Abrir el calendario
        actor.attemptsTo(
                Click.on(DATEPICKER_FIELD)
        );

        // Navegar hasta el mes y año correcto
        while (true) {
            String mesActual = MONTH_LABEL.resolveFor(actor).getText();
            String añoActual = YEAR_LABEL.resolveFor(actor).getText();

            if (mesActual.equals(mes) && añoActual.equals(año)) break;

            actor.attemptsTo(Click.on(NEXT_BUTTON));
        }

        // Seleccionar el día
        Target DIA_LOCATOR = Target.the("día buscado")
                .located(By.xpath("//a[text()='" + dia + "']"));

        actor.attemptsTo(
                Click.on(DIA_LOCATOR)
        );
    }

    public static ValidarRangoYPersistencia conFechas(
            String fechaMin, String fechaMax,
            String dia, String mes, String año) {

        return instrumented(ValidarRangoYPersistencia.class,
                fechaMin, fechaMax, dia, mes, año);
    }
}
